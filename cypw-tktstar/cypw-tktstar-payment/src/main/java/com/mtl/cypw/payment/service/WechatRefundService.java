package com.mtl.cypw.payment.service;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.UuidUtil;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.payment.param.WechatPayRefundRequestParam;
import com.mtl.cypw.payment.utils.WechatServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tang.
 * @date 2019/12/23.
 */
@Service
@Slf4j
public class WechatRefundService {

    public WxPayRefundResult refund(WechatPayRefundRequestParam param, WxConfigure config) {
        WxPayRefundResult refundResult;
        WxPayService wxPayService = WechatServiceUtils.getWxPayService(config);
        WxPayRefundRequest refundRequest = createWxPayRefundRequest(param, config);
        try {
            refundResult = wxPayService.refund(refundRequest);
        } catch (WxPayException e) {
            log.error("退款异常", e);
            throw new BusinessException(ErrorCode.ERROR_PAY.getCode());
        }
        return refundResult;
    }

    private WxPayRefundRequest createWxPayRefundRequest(WechatPayRefundRequestParam param, WxConfigure config) {
        WxPayRefundRequest request = new WxPayRefundRequest();
        request.setAppid(config.getAppId());
        request.setMchId(config.getMchId());
        request.setNonceStr(UuidUtil.getUuid());
        request.setRefundFeeType(WxConfigure.FEETYPE);
        request.setOutTradeNo(param.getOrderNo());
        request.setOutRefundNo(param.getOutRefundNo());
        request.setTotalFee(param.getRefundFee().intValue());
        request.setRefundFee(param.getRefundFee());
        request.setNotifyUrl(param.getNotifyUrl());

        return request;
    }
}
