package com.mtl.cypw.provider.payment.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.payment.endpoint.PayPalApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.PayPalConfigure;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.param.PayPalExecutePaymentParam;
import com.mtl.cypw.domain.payment.param.PayPalRequestParam;
import com.mtl.cypw.domain.payment.result.PayPalResult;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.payment.service.PayPalService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.payment.service.PaymentCallbackService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@RestController
@Slf4j
public class PayPalEndpoint implements PayPalApi {
    @Resource
    PayPalService payPalService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    PaymentCallbackService paymentCallbackService;

    @Resource
    OrderQueryService orderQueryServiceImpl;

    @Override
    public TSingleResult<PayPalResult> createPayment(GenericRequest<PayPalRequestParam> request) {
        try {
            log.debug("PayPal支付，request:{}", request);
            PayPalRequestParam param = request.getParam();
            if (param.checkParam()) {
                try {
                    PayPalResult result = new PayPalResult();
                    PayPalConfigure configure = enterpriseConfigService.getPayPalConfigure(param.getEnterpriseId());
                    Payment payment = payPalService.createPayment(param, configure);
                    for(Links links : payment.getLinks()){
                        if(links.getRel().equals("approval_url")){
                            // 客户付款登陆地址
                            result.setJsonRequestData(links.getHref());
                            result.setMethod(links.getMethod());
                            result.setRel(links.getRel());
                        }
                    }
                    return ResultBuilder.succTSingle(result);
                } catch (Exception e) {
                    log.error("PayPal支付异常：{}", e.getMessage());
                    throw new BusinessException(ErrorCode.ERROR_PAY.getMsg(), ErrorCode.ERROR_PAY.getCode());
                }
            } else {
                log.error("PayPal支付参数异常,request:{}", JSONObject.toJSONString(param));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
        } catch (BusinessException e) {
            log.error("PayPal支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

    @Override
    public TSingleResult<Integer> executePayment(GenericRequest<PayPalExecutePaymentParam> request) {
        try {
            PayPalExecutePaymentParam param = request.getParam();
            if (param == null) {
                log.error("PayPal支付回调参数异常,request:{}", JSONObject.toJSONString(request));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
            Payment result = payPalService.executePayment(param.getPaymentId(), param.getPayerId());
            log.debug("支付结果：result：{}", JSONObject.toJSONString(result));
            if ("approved".equals(result.getState())) {
                log.info("支付成功");
                String orderNo = result.getTransactions().get(0).getInvoiceNumber();
                String transactionId = result.getTransactions().get(0).getRelatedResources().get(0).getOrder().getId();
                Order order = orderQueryServiceImpl.findOneByOrderNo(orderNo);
                if (order != null) {
                    order.setTransactionFlowNo(transactionId);
                    paymentCallbackService.paid(order, PayTypeEnum.PAY_PAL, transactionId);
                    return ResultBuilder.succTSingle(order.getId());
                } else {
                    log.error("没找到订单，orderNo:{}", orderNo);
                    throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
                }
            } else {
                log.error("PayPal订单支付失败");
                throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
            }
        } catch (BusinessException e) {
            log.error("支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付回调异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }
}
