package com.mtl.cypw.wx.offiaccount;

import com.juqitech.service.utils.StringUtil;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.wx.response.WxConfigResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Service;

/**
 * 对接公众号接口
 *
 * @author tang.
 * @date 2019/12/9.
 */
@Slf4j
@Service
public class OffiaccountService {

    public WxConfigResponse getConfigInfo(String url, WxConfigure config) {
        if (StringUtil.isEmpty(url)) {
            return null;
        }
        WxMpService wxMpService = getWxMpService(config);
        WxJsapiSignature jsapiSignature = null;
        try {
            jsapiSignature = wxMpService.createJsapiSignature(url);
            WxConfigResponse wxConfigVo = new WxConfigResponse();
            wxConfigVo.setAppId(jsapiSignature.getAppId());
            wxConfigVo.setNonceStr(jsapiSignature.getNonceStr());
            wxConfigVo.setSignature(jsapiSignature.getSignature());
            wxConfigVo.setTimestamp(jsapiSignature.getTimestamp());
            wxConfigVo.setUrl(jsapiSignature.getUrl());
            return wxConfigVo;
        } catch (WxErrorException e) {
            log.error("创建微信分享链接失败：{}", e.getError());
        }
        return null;
    }

    public String getOpenIdByCode(String code, WxConfigure config) {
        log.info("根据code获取openID");
        WxMpService wxMpService = getWxMpService(config);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("获取openid失败：{}", e.getError());
        }
        return wxMpOAuth2AccessToken.getOpenId();
    }

    public static WxMpService getWxMpService(WxConfigure configure) {
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(configure.getAppId());
        wxMpDefaultConfig.setSecret(configure.getSecret());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpDefaultConfig);
        return wxMpService;
    }
}
