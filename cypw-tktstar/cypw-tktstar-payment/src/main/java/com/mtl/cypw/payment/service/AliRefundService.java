package com.mtl.cypw.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.payment.config.AliConfigure;
import com.mtl.cypw.domain.payment.param.RefundRequestParam;
import com.mtl.cypw.domain.payment.result.AliPayResult;
import com.mtl.cypw.payment.request.AliRefundRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author tang.
 * @date 2020/3/11.
 */
@Slf4j
@Service
public class AliRefundService {

    public Boolean refund(RefundRequestParam param, AliConfigure config) throws AlipayApiException {
        AliRefundRequest requestData = new AliRefundRequest();
        requestData.setOut_trade_no(param.getOrderNo());
        requestData.setRefund_amount(new BigDecimal(MoneyUtils.getMoneyByCent(param.getRefundAmount()).toString()));
        requestData.setOut_request_no(param.getOrderRefundNo());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setNotifyUrl(param.getNotifyUrl());
        request.setBizContent(JSONObject.toJSONString(requestData));
        AlipayClient alipayClient = new DefaultAlipayClient(AliRefundRequest.refundUrl, config.getAppId(), config.getPrivateKey(), "json", "utf-8", config.getAliPublicKey(), config.getSignType());
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            log.info("退款成功");
            return true;
        } else {
            log.info("退款失败");
            return false;
        }
    }

}
