package com.mtl.cypw.payment.utils;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.mtl.cypw.domain.payment.config.WxConfigure;

/**
 * @author tang.
 * @date 2019/12/23.
 */
public class WechatServiceUtils {

    public static WxPayService getWxPayService(WxConfigure configure) {
        WxPayConfig config = new WxPayConfig();
        config.setAppId(configure.getAppId());
        config.setMchId(configure.getMchId());
        config.setMchKey(configure.getKey());
        config.setKeyPath(configure.getCertLocalPath());
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(config);
        return wxPayService;
    }

}
