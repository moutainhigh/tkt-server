package com.mtl.cypw.api.wx.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.wx.request.WxRequest;
import com.mtl.cypw.domain.wx.response.MiniappSessionResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/10.
 */
public interface MiniappApi {

    /**
     * 根据code 获取openid,sessionKey
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/wx/miniapp/get/session")
    TSingleResult<MiniappSessionResult> getJscode2Session(@RequestBody GenericRequest<WxRequest> request);
}
