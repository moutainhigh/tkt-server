package com.mtl.cypw.api.auth.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.auth.dto.CmbAuthDTO;
import com.mtl.cypw.domain.auth.param.AuthCheckSignParam;
import com.mtl.cypw.domain.auth.request.CmbRequest;
import com.mtl.cypw.domain.auth.response.CmbAuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/1/15.
 */
public interface CmbApi {

    /**
     * 招行授权
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/cmb/auth")
    TSingleResult<CmbAuthResponse> auth(@RequestBody GenericRequest<CmbRequest> request);

    /**
     * 招行授权数据，解析验证
     * @param request
     * @return
     */
    @PostMapping("/endpoint/cmb/auth/check")
    TSingleResult<CmbAuthDTO> checkSign(@RequestBody GenericRequest<AuthCheckSignParam> request);
}
