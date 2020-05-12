package com.mtl.cypw.provider.payment.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.payment.endpoint.AliPayApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.payment.callback.AliPayCallbackParam;
import com.mtl.cypw.domain.payment.config.AliConfigure;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.param.AliPayRequestParam;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.result.AliPayResult;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.payment.service.AliPayService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.payment.service.PaymentCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@RestController
@Slf4j
public class AliPayEndpoint implements AliPayApi {

    @Resource
    AliPayService aliPayService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    OrderQueryService orderQueryServiceImpl;

    @Resource
    private PaymentCallbackService paymentCallbackService;

    @Override
    public TSingleResult<AliPayResult> unifiedOrder(GenericRequest<AliPayRequestParam> request) {
        log.debug("阿里支付，request:{}", request);
        AliPayRequestParam payRequest = request.getParam();
        if (payRequest.checkParam()) {
            try {
                AliConfigure configure = enterpriseConfigService.getAliConfigure(payRequest.getEnterpriseId());
                AliPayResult aliPayResult = aliPayService.unifiedOrder(request.getParam(), configure);
                TSingleResult<AliPayResult> result = ResultBuilder.succTSingle(aliPayResult);
                return result;
            } catch (BusinessException e) {
                log.error("阿里支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
                return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
            } catch (Exception e) {
                log.error("阿里支付异常：{}", e.getMessage());
            }
        }
        log.error("支付异常");
        return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
    }

    @Override
    public TSingleResult<Boolean> checkSign(GenericRequest<PayCheckSignParam> request) {
        try {
            PayCheckSignParam param = request.getParam();
            if (param == null) {
                log.error("阿里支付回调参数异常,request:{}", JSONObject.toJSONString(request));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
            AliPayCallbackParam response = JSONObject.toJavaObject(JSONObject.parseObject(param.getResponseStr()), AliPayCallbackParam.class);
            if (response != null) {
                Order order = orderQueryServiceImpl.findOneByOrderNo(response.getOut_trade_no());
                if (order != null) {
                    AliConfigure configure = enterpriseConfigService.getAliConfigure(order.getEnterpriseId());
                    Boolean signVerified = aliPayService.checkSign(response, configure);
                    if (signVerified) {
                        if (response.getTotal_amount().equals(MoneyUtils.getMoneyByCent(order.getActualAmount()).toString()) && configure.getAppId().equals(response.getApp_id())) {
                            order.setTransactionFlowNo(response.getTrade_no());
                            return paymentCallbackService.paid(order, PayTypeEnum.ALI_PAY, param.getResponseStr());
                        } else {
                            log.error("回调数据不一致，response.amount:{},response.appId:{},orderAmount:{},appId:{}", response.getTotal_amount(), response.getApp_id(), order.getActualAmount(), configure.getAppId());
                            throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
                        }
                    } else {
                        log.error("验签失败");
                        throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
                    }
                } else {
                    log.error("没找到订单，orderNo:{}", response.getOut_trade_no());
                    throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
                }
            } else {
                log.error("订单支付失败");
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
