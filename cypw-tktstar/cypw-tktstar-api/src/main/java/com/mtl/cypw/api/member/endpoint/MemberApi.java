package com.mtl.cypw.api.member.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.member.dto.MemberDTO;
import com.mtl.cypw.domain.member.param.MemberLoginParam;
import com.mtl.cypw.domain.member.param.MemberParam;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/22.
 */
public interface MemberApi {
    /**
     * 查询演出场次列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/getMemberById")
    TSingleResult<MemberDTO> getMemberById(@RequestBody QueryRequest<MemberQueryParam> request);

    /**
     * 用户登录，未注册的自动注册
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/registerAndLogin")
    TSingleResult<MemberDTO> registerAndLogin(@RequestBody QueryRequest<MemberLoginParam> request);

    /**
     * 修改用户信息
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/update")
    TSingleResult<Boolean> updateMember(@RequestBody GenericRequest<MemberParam> request);
}
