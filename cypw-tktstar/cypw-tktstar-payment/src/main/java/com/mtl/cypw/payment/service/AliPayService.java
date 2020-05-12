package com.mtl.cypw.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.BeanUtil;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.payment.callback.AliPayCallbackParam;
import com.mtl.cypw.domain.payment.config.AliConfigure;
import com.mtl.cypw.domain.payment.enums.AliPayTypeEnum;
import com.mtl.cypw.domain.payment.param.AliPayRequestParam;
import com.mtl.cypw.domain.payment.result.AliPayResult;
import com.mtl.cypw.payment.request.AliPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


/**
 * @author tang.
 * @date 2019/12/9.
 */
@Service
@Slf4j
public class AliPayService {

    public AliPayResult unifiedOrder(AliPayRequestParam param, AliConfigure config) throws AlipayApiException {
        AliPayRequest requestData = new AliPayRequest();
        requestData.setSubject(param.getSubject());
        requestData.setOut_trade_no(param.getOrderNo());
        requestData.setTotal_amount(new BigDecimal(MoneyUtils.getMoneyByCent(param.getOrderAmount()).toString()));
        requestData.setQuit_url(param.getQuitUrl());
        requestData.setProduct_code("FAST_INSTANT_TRADE_PAY");
        if (AliPayTypeEnum.WAP.equals(param.getAliPayTypeEnum())) {
            return aliPayTradeWapPayRequest(param, config, requestData);
        } else if (AliPayTypeEnum.PAGE.equals(param.getAliPayTypeEnum())) {
            return aliPayTradePagePayRequest(param, config, requestData);
        } else if (AliPayTypeEnum.APP.equals(param.getAliPayTypeEnum())) {
            return aliPayTradeAppPayRequest(param, config, requestData);
        } else {
            log.error("错误的支付类型");
        }
        throw new BusinessException(ErrorCode.ERROR_PAY_TYPE.getMsg(), ErrorCode.ERROR_PAY_TYPE.getCode());
    }

    private AliPayResult aliPayTradeWapPayRequest(AliPayRequestParam param, AliConfigure config, AliPayRequest requestData) throws AlipayApiException {
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl(param.getNotifyUrl());
        request.setBizContent(JSONObject.toJSONString(requestData));
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayRequest.payUrl, config.getAppId(), config.getPrivateKey(), "json", "utf-8", config.getPublicKey(), config.getSignType());
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
        AliPayResult result = new AliPayResult();
        result.setBody(response.getBody());
        return result;
    }

    private AliPayResult aliPayTradeAppPayRequest(AliPayRequestParam param, AliConfigure config, AliPayRequest requestData) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(param.getNotifyUrl());
        request.setBizContent(JSONObject.toJSONString(requestData));
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayRequest.payUrl, config.getAppId(), config.getPrivateKey(), "json", "utf-8", config.getPublicKey(), config.getSignType());
        AlipayTradeAppPayResponse response = alipayClient.pageExecute(request);
        AliPayResult result = new AliPayResult();
        result.setBody(response.getBody());
        return result;
    }

    private AliPayResult aliPayTradePagePayRequest(AliPayRequestParam param, AliConfigure config, AliPayRequest requestData) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(param.getNotifyUrl());
        request.setBizContent(JSONObject.toJSONString(requestData));
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayRequest.payUrl, config.getAppId(), config.getPrivateKey(), "json", "utf-8", config.getPublicKey(), config.getSignType());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        AliPayResult result = new AliPayResult();
        result.setBody(response.getBody());
        return result;
    }

    public Boolean checkSign(AliPayCallbackParam param, AliConfigure config) throws AlipayApiException {
        Map<String, String> paramsMap = BeanUtil.converterMapToString(param);
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, config.getAliPublicKey(), param.getCharset(), param.getSign_type());
        return signVerified;
    }
}
