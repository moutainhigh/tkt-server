package com.mtl.cypw.wx.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaMsgServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.wx.param.MiniappSubscribeMessageParam;
import com.mtl.cypw.domain.wx.response.MiniappSessionResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.collections.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 对接小程序接口
 *
 * @author tang.
 * @date 2020/3/4.
 */
@Slf4j
@Service
public class MiniappService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendSubscribeMsg(MiniappSubscribeMessageParam param, WxConfigure config) {
        WxMaSubscribeMessage message = new WxMaSubscribeMessage();
        message.setTemplateId(param.getTemplateId());
        message.setToUser(param.getToUser());
        message.setPage(param.getPage());
        if (CollectionUtils.isNotEmpty(param.getDataList())) {
            param.getDataList().forEach(n -> message.addData(new WxMaSubscribeMessage.Data(n.getName(), n.getValue())));
        }

        WxMaMsgService wxMaMsgService = getWxMaMsgService(config);
        try {
            wxMaMsgService.sendSubscribeMsg(message);
            log.info("发送成功");
        } catch (WxErrorException e) {
            log.error("e:{}", e.getMessage());
            throw new BusinessException(e.getError().getErrorMsg(), e.getError().getErrorCode());
        }
    }

    /**
     * 获取小程序openID和sessionKey
     *
     * @param code
     * @param config
     * @return
     */
    public MiniappSessionResult getJscode2Session1(String code, WxConfigure config) {
        //第三方jar包报错，jar包内部引用lang3.jar失败
        MiniappSessionResult result = new MiniappSessionResult();
        try {
            WxMaService wxMaService = getWxMaService(config);
            WxMaJscode2SessionResult sessionResult = wxMaService.jsCode2SessionInfo(code);
            if (sessionResult != null) {
                result.setOpenid(sessionResult.getOpenid());
                result.setSessionKey(sessionResult.getSessionKey());
                result.setUnionId(sessionResult.getUnionid());
            }
            return result;
        } catch (WxErrorException e) {
            log.error("e:{}", e.getMessage());
            throw new BusinessException(e.getError().getErrorMsg(), e.getError().getErrorCode());
        }
    }

    public MiniappSessionResult getJscode2Session(String code, WxConfigure config) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&appid=" + config.getAppId() + "&secret=" + config.getSecret() + "&js_code=" + code;
        String str = restTemplate.getForObject(url, String.class);
        JSONObject response = (JSONObject) JSONObject.parse(str);
        MiniappSessionResult result = new MiniappSessionResult();
        result.setSessionKey(response.getString("session_key"));
        result.setOpenid(response.getString("openid"));
        result.setUnionId(response.getString("unionid"));
        return result;
    }

    private static WxMaService getWxMaService(WxConfigure config) {
        WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
        wxMaConfig.setAppid(config.getAppId());
        wxMaConfig.setSecret(config.getSecret());
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig);
        return wxMaService;
    }

    private static WxMaMsgService getWxMaMsgService(WxConfigure config) {
        WxMaMsgService wxMaMsgService = new WxMaMsgServiceImpl(getWxMaService(config));
        return wxMaMsgService;
    }
}
