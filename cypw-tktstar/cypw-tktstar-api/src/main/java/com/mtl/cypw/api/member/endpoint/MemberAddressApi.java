package com.mtl.cypw.api.member.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.member.dto.MemberAddressDTO;
import com.mtl.cypw.domain.member.param.MemberAddressParam;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/22.
 */
public interface MemberAddressApi {
    /**
     * 查询演出场次列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/address")
    TMultiResult<MemberAddressDTO> searchMemberAddressList(@RequestBody QueryRequest<MemberQueryParam> request);

    /**
     * 新增会员地址
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/address/create")
    TSingleResult<Boolean> createMemberAddress(@RequestBody GenericRequest<MemberAddressParam> request);

    /**
     * 修改会员地址
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/address/update")
    TSingleResult<Boolean> updateMemberAddress(@RequestBody GenericRequest<MemberAddressParam> request);

    /**
     * 删除会员地址
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/address/delete")
    TSingleResult<Boolean> deleteMemberAddress(@RequestBody IdRequest request);

    /**
     * 根据地址id查询会员地址
     * @param request
     * @return
     */
    @PostMapping("/endpoint/member/address/getAddress")
    TSingleResult<MemberAddressDTO> getMemberAddress(@RequestBody IdRequest request);

    /**
     * 修改会员默认地址
     * @param request 地址ID
     * @return
     */
    @PostMapping("/endpoint/member/address/default")
    TSingleResult<Boolean> updateDefaultAddress(@RequestBody IdRequest request);
}
