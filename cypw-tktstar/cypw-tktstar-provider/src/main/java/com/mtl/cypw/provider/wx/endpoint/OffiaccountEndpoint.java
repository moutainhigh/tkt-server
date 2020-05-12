package com.mtl.cypw.provider.wx.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.wx.endpoint.OffiaccountApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.wx.request.WxRequest;
import com.mtl.cypw.domain.wx.response.WxConfigResponse;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.wx.offiaccount.OffiaccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/10.
 */
@RestController
@Slf4j
public class OffiaccountEndpoint implements OffiaccountApi {

    @Resource
    OffiaccountService offiaccountService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Override
    public TSingleResult<WxConfigResponse> getConfigInfo(GenericRequest<WxRequest> request) {
        log.debug("获取微信分享参数，request:{}", request);
        WxRequest wxRequest = request.getParam();
        if (wxRequest.getEnterpriseId() != null && StringUtils.isNotEmpty(wxRequest.getUrl())) {
            WxConfigure configure = enterpriseConfigService.getWxConfigure(wxRequest.getEnterpriseId());
            WxConfigResponse configInfo = offiaccountService.getConfigInfo(wxRequest.getUrl(), configure);
            return ResultBuilder.succTSingle(configInfo);
        }
        log.error("获取微信分享参数异常");
        return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
    }

    @Override
    public TSingleResult<String> getOpenIdByCode(GenericRequest<WxRequest> request) {
        log.debug("获取openid，request:{}", request);
        WxRequest wxRequest = request.getParam();
        if (wxRequest.getEnterpriseId() != null && StringUtils.isNotEmpty(wxRequest.getCode())) {
            WxConfigure configure = enterpriseConfigService.getWxConfigure(wxRequest.getEnterpriseId());
            String openId = offiaccountService.getOpenIdByCode(wxRequest.getCode(), configure);
            return ResultBuilder.succTSingle(openId);
        }
        log.error("获取微信openid异常");
        return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
    }

}
