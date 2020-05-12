package com.mtl.cypw.provider.payment.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.payment.endpoint.WechatPayApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.SignUtil;
import com.mtl.cypw.common.utils.UuidUtil;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.enums.WechatPayTypeEnum;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.param.WechatPayRequestParam;
import com.mtl.cypw.domain.payment.result.WechatPayResult;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.payment.service.WechatPayService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.payment.service.PaymentCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@RestController
@Slf4j
@Service("wechatPayEndpoint")
public class WechatPayEndpoint implements WechatPayApi {

    @Resource
    WechatPayService wechatPayService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    OrderQueryService orderQueryServiceImpl;

    @Resource
    private PaymentCallbackService paymentCallbackService;

    @Override
    public TSingleResult<WechatPayResult> unifiedOrder(GenericRequest<WechatPayRequestParam> request) {
        try {
            log.info("微信支付，request:{}", request);
            WechatPayRequestParam param = request.getParam();
            if (param.checkParam()) {
                WxConfigure configure = enterpriseConfigService.getWxConfigure(param.getEnterpriseId());
                WxPayUnifiedOrderResult payResult = wechatPayService.unifiedOrder(request.getParam(), configure);
                if (payResult.getResultCode() != null && "SUCCESS".equals(payResult.getResultCode()) && payResult.getPrepayId() != null) {
                    WechatPayResult wechatPayResult = new WechatPayResult();
                    if (WechatPayTypeEnum.NATIVE.equals(param.getWechatPayTypeEnum())) {
                        wechatPayResult.setCodeUrl(payResult.getCodeURL());
                    } else if (WechatPayTypeEnum.MWEB.equals(param.getWechatPayTypeEnum())) {
                        wechatPayResult.setMwebUrl(payResult.getMwebUrl());
                    } else if (WechatPayTypeEnum.JSAPI.equals(param.getWechatPayTypeEnum())) {
                        wechatPayResult.setAppId(payResult.getAppid());
                        wechatPayResult.setNonceStr(UuidUtil.getUuid());
                        wechatPayResult.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
                        wechatPayResult.setPrepayId(payResult.getPrepayId());
                        wechatPayResult.setSignType("MD5");
                        Map<String, Object> payParams = new HashMap<String, Object>();
                        payParams.put("appId", wechatPayResult.getAppId());
                        payParams.put("nonceStr", wechatPayResult.getNonceStr());
                        payParams.put("timeStamp", wechatPayResult.getTimeStamp());
                        payParams.put("package", "prepay_id=" + wechatPayResult.getPrepayId());
                        payParams.put("signType", wechatPayResult.getSignType());
                        String sign = SignUtil.getSignByWechat(payParams, configure.getKey());
                        wechatPayResult.setSign(sign);
                    } else {
                        log.error("不支持的支付类型");
                        return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY_TYPE.getCode(), ErrorCode.ERROR_PAY_TYPE.getMsg());
                    }
                    TSingleResult<WechatPayResult> result = ResultBuilder.succTSingle(wechatPayResult);
                    return result;
                } else {
                    log.error("支付异常,result:{}", JSONObject.toJSONString(payResult));
                    return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
                }
            } else {
                log.error("微信支付参数异常,request:{}", JSONObject.toJSONString(param));
                return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY_PARAMETER.getCode(), ErrorCode.ERROR_PAY_PARAMETER.getMsg());
            }
        } catch (BusinessException e) {
            log.error("微信支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> checkSign(GenericRequest<PayCheckSignParam> request) {
        try {
            PayCheckSignParam param = request.getParam();
            if (param == null) {
                log.error("微信支付回调参数异常,request:{}", JSONObject.toJSONString(request));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
            WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(param.getResponseStr());
            if (result != null && "SUCCESS".equals(result.getReturnCode())) {
                Order order = orderQueryServiceImpl.findOneByOrderNo(result.getOutTradeNo());
                if (order != null) {
                    WxConfigure configure = enterpriseConfigService.getWxConfigure(order.getEnterpriseId());
                    wechatPayService.checkSign(param.getResponseStr(), configure);
                    order.setTransactionFlowNo(result.getTransactionId());
                    return paymentCallbackService.paid(order, PayTypeEnum.WECHAT_PAY, param.getResponseStr());
                } else {
                    log.error("没找到订单，orderNo:{}", result.getOutTradeNo());
                    return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY_FAIL.getCode(), ErrorCode.ERROR_PAY_FAIL.getMsg());
                }
            } else {
                log.error("订单支付失败");
                return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY_FAIL.getCode(), ErrorCode.ERROR_PAY_FAIL.getMsg());
            }
        } catch (BusinessException e) {
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付回调异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

}
