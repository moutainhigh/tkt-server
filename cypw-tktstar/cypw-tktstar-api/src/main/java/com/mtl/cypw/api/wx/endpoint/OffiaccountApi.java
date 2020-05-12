package com.mtl.cypw.api.wx.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.wx.request.WxRequest;
import com.mtl.cypw.domain.wx.response.WxConfigResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/10.
 */
public interface OffiaccountApi {
    /**
     * 获取微信分享参数
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/wx/offiaccount/get/config")
    TSingleResult<WxConfigResponse> getConfigInfo(@RequestBody GenericRequest<WxRequest> request);

    /**
     * 根据code获取openid
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/wx/offiaccount/get/openid")
    TSingleResult<String> getOpenIdByCode(@RequestBody GenericRequest<WxRequest> request);
}
