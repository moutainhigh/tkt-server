package com.mtl.cypw.api.member.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.member.dto.MemberSignInDTO;
import com.mtl.cypw.domain.member.param.MemberSignInParam;
import com.mtl.cypw.domain.member.param.MemberSignInQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/3.
 */
public interface MemberSignInApi {
    /**
     * 用户签到
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/add/sign_in")
    TSingleResult<Boolean> addMemberSignIn(@RequestBody GenericRequest<MemberSignInParam> request);

    /**
     * 查询用户签到列表
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/search/sign_in")
    TMultiResult<MemberSignInDTO> searchMemberSignInList(@RequestBody QueryRequest<MemberSignInQueryParam> request);
}
