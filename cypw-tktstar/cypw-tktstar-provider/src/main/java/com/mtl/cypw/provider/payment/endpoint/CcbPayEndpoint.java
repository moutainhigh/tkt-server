package com.mtl.cypw.provider.payment.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.payment.endpoint.CcbPayApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.callback.CcbCallbackParam;
import com.mtl.cypw.domain.payment.config.CcbConfigure;
import com.mtl.cypw.domain.payment.enums.CcbPayTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.param.CcbPayRequestParam;
import com.mtl.cypw.domain.payment.result.CcbPayResult;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.payment.service.CcbPayService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.payment.service.PaymentCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/8.
 */
@RestController
@Slf4j
public class CcbPayEndpoint implements CcbPayApi {

    @Resource
    CcbPayService ccbPayService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    OrderQueryService orderQueryServiceImpl;

    @Resource
    private PaymentCallbackService paymentCallbackService;

    @Override
    public TSingleResult<CcbPayResult> unifiedOrder(GenericRequest<CcbPayRequestParam> request) {
        try {
            log.debug("建行支付，request:{}", request);
            CcbPayRequestParam param = request.getParam();
            if (param.checkParam()) {
                try {
                    CcbConfigure configure = enterpriseConfigService.getCcbConfigure(param.getEnterpriseId());
                    String jsonRequestData = "";
                    if (CcbPayTypeEnum.APP.equals(param.getCcbPayTypeEnum())) {

                    } else if (CcbPayTypeEnum.H5.equals(param.getCcbPayTypeEnum())) {
                        jsonRequestData = ccbPayService.combineH5PayData(request.getParam(), configure);
                    } else {
                        throw new BusinessException(ErrorCode.ERROR_PAY_TYPE.getMsg(), ErrorCode.ERROR_PAY_TYPE.getCode());
                    }
                    CcbPayResult ccbPayResult = new CcbPayResult();
                    ccbPayResult.setJsonRequestData(jsonRequestData);
                    return ResultBuilder.succTSingle(ccbPayResult);
                } catch (Exception e) {
                    log.error("建行支付异常：{}", e.getMessage());
                    throw new BusinessException(ErrorCode.ERROR_PAY.getMsg(), ErrorCode.ERROR_PAY.getCode());
                }
            } else {
                log.error("建行支付参数异常,request:{}", JSONObject.toJSONString(param));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
        } catch (BusinessException e) {
            log.error("建行支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> checkSign(GenericRequest<CcbCallbackParam> request) {
        CcbCallbackParam param = request.getParam();
        if (param == null || StringUtils.isEmpty(param.getOrderId())) {
            log.error("建行支付回调参数异常,param:{}", JSONObject.toJSONString(param));
            throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
        }
        Order order = orderQueryServiceImpl.findOneByOrderNo(param.getOrderId());
        if (order != null) {
            CcbConfigure configure = enterpriseConfigService.getCcbConfigure(order.getEnterpriseId());
            Boolean b = ccbPayService.checkSign(param, configure);
            if (b) {
                log.debug("验签成功");
                if ("Y".equalsIgnoreCase(param.getSuccess())) {
                    order.setTransactionFlowNo(param.getOrderId());
                    return paymentCallbackService.paid(order, PayTypeEnum.CCB_PAY, param.toString());
                } else {
                    log.error("订单支付失败");
                    throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
                }
            } else {
                log.error("验签失败");
                throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
            }
        } else {
            log.error("没找到订单，orderNo:{}", param.getOrderId());
            throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
        }
    }

}
