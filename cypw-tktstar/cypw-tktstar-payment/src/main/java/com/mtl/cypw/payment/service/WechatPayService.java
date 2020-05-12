package com.mtl.cypw.payment.service;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.SignUtil;
import com.mtl.cypw.common.utils.UuidUtil;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.payment.enums.WechatPayTypeEnum;
import com.mtl.cypw.domain.payment.param.WechatPayRequestParam;
import com.mtl.cypw.payment.utils.WechatServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Service
@Slf4j
public class WechatPayService {

    public WxPayUnifiedOrderResult unifiedOrder(WechatPayRequestParam param, WxConfigure config) {
        WxPayUnifiedOrderRequest request = createWxUnifiedOrderReqDto(param, config);
        WxPayService wxPayService = WechatServiceUtils.getWxPayService(config);
        WxPayUnifiedOrderResult result;
        try {
            result = wxPayService.unifiedOrder(request);
        } catch (Exception e) {
            log.error("统一下单异常", e.getMessage());
            throw new BusinessException(ErrorCode.ERROR_PAY.getCode());
        }
        return result;

    }

    private WxPayUnifiedOrderRequest createWxUnifiedOrderReqDto(WechatPayRequestParam param, WxConfigure config) {
        WxPayUnifiedOrderRequest unifiedOrder = new WxPayUnifiedOrderRequest();
        unifiedOrder.setAppid(config.getAppId());
        unifiedOrder.setMchId(config.getMchId());
        unifiedOrder.setDeviceInfo(WxConfigure.DEVICEINFO);
        unifiedOrder.setBody(param.getProduct());
        unifiedOrder.setNonceStr(UuidUtil.getUuid());
        unifiedOrder.setOutTradeNo(param.getOrderNo());
        unifiedOrder.setFeeType(WxConfigure.FEETYPE);

        unifiedOrder.setTotalFee(param.getOrderAmount().intValue());
        unifiedOrder.setSpbillCreateIp(param.getRemoteAddress());
        unifiedOrder.setNotifyUrl(param.getNotifyUrl());
        if (WechatPayTypeEnum.NATIVE.equals(param.getWechatPayTypeEnum())) {
            //扫码支付
            unifiedOrder.setTradeType(WxConfigure.TRADE_TYPE.NATIVE);
            unifiedOrder.setProductId(param.getOrderNo());
        } else if (WechatPayTypeEnum.MWEB.equals(param.getWechatPayTypeEnum())) {
            //H5支付
            unifiedOrder.setTradeType(WxConfigure.TRADE_TYPE.MWEB);
            unifiedOrder.setSceneInfo(param.getSceneInfo());
        } else if (WechatPayTypeEnum.JSAPI.equals(param.getWechatPayTypeEnum())) {
            //JSAPI支付
            unifiedOrder.setTradeType(WxConfigure.TRADE_TYPE.JSAPI);
            unifiedOrder.setOpenid(param.getOpenid());
        }
        return unifiedOrder;
    }

    public Boolean checkSign(String xml, WxConfigure config) throws BusinessException {
        log.debug("微信支付回调数据,xml:{}", xml);
        try {
            if (SignUtil.checkIsSignValidForWechat(xml, config.getKey())) {
                log.debug("签名校验成功");
                return true;
            } else {
                log.error("签名校验失败");
                throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
            }
        } catch (Exception e) {
            log.error("签名校验异常，异常信息：{}", e.getMessage());
            throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
        }
    }

}
