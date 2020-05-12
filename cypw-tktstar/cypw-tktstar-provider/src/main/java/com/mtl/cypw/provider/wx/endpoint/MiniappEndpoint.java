package com.mtl.cypw.provider.wx.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.wx.endpoint.MiniappApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.wx.request.WxRequest;
import com.mtl.cypw.domain.wx.response.MiniappSessionResult;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.wx.miniapp.MiniappService;
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
public class MiniappEndpoint implements MiniappApi {
    @Resource
    MiniappService miniappService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Override
    public TSingleResult<MiniappSessionResult> getJscode2Session(GenericRequest<WxRequest> request) {
        log.debug("获取openid，request:{}", request);
        WxRequest wxRequest = request.getParam();
        if (wxRequest.getEnterpriseId() != null && StringUtils.isNotEmpty(wxRequest.getCode())) {
            WxConfigure configure = enterpriseConfigService.getWxConfigure(wxRequest.getEnterpriseId());
            MiniappSessionResult result = miniappService.getJscode2Session(wxRequest.getCode(), configure);
            return ResultBuilder.succTSingle(result);
        }
        log.error("获取微信openid异常");
        return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
    }
}
