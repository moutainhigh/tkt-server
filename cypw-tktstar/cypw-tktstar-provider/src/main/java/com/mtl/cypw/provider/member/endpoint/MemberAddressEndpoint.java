package com.mtl.cypw.provider.member.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.member.endpoint.MemberAddressApi;
import com.mtl.cypw.domain.member.dto.MemberAddressDTO;
import com.mtl.cypw.domain.member.param.MemberAddressParam;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import com.mtl.cypw.member.pojo.MemberAddress;
import com.mtl.cypw.member.service.MemberAddressService;
import com.mtl.cypw.member.service.MemberService;
import com.mtl.cypw.provider.member.converter.MemberAddressConverter;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@RestController
public class MemberAddressEndpoint implements MemberAddressApi {

    @Resource
    MemberAddressService memberAddressService;

    @Resource
    MemberAddressConverter memberAddressConverter;

    @Resource
    MemberService memberService;

    @Override
    public TMultiResult<MemberAddressDTO> searchMemberAddressList(QueryRequest<MemberQueryParam> request) {
        List<MemberAddress> list = memberAddressService.searchMemberAddressList(request.getParam());
        return ResultBuilder.succTMulti(memberAddressConverter.toDto(list));
    }

    @Override
    public TSingleResult<Boolean> createMemberAddress(GenericRequest<MemberAddressParam> request) {
        TSingleResult result = ResultBuilder.succTSingle(true);
        MemberAddress memberAddress = memberAddressConverter.toEntity(request.getParam());
        if (memberAddress != null) {
            memberAddress.setAddressId(null);
            memberAddressService.createMemberAddress(memberAddress);
            if (memberAddress.getIsDefault() == 1) {
                updateDefaultAddress(memberAddress.getAddressId());
            }
        }
        return result;
    }

    @Override
    public TSingleResult<Boolean> updateMemberAddress(GenericRequest<MemberAddressParam> request) {
        TSingleResult result = ResultBuilder.succTSingle(true);
        MemberAddress memberAddress = memberAddressConverter.toEntity(request.getParam());
        if (memberAddress == null || memberAddress.getAddressId() == null) {
            result.setData(false);
        } else {
            memberAddressService.updateMemberAddress(memberAddress);
            if (memberAddress.getIsDefault() == 1) {
                updateDefaultAddress(memberAddress.getAddressId());
            }
        }
        return result;
    }

    @Override
    public TSingleResult<Boolean> deleteMemberAddress(IdRequest request) {
        memberAddressService.deleteMemberAddress(Integer.parseInt(request.getId()));
        TSingleResult result = ResultBuilder.succTSingle(true);
        return result;
    }

    @Override
    public TSingleResult<MemberAddressDTO> getMemberAddress(IdRequest request) {
        MemberAddress memberAddress = memberAddressService.getMemberAddress(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(memberAddressConverter.toDto(memberAddress));
    }

    @Override
    public TSingleResult<Boolean> updateDefaultAddress(IdRequest request) {
        return updateDefaultAddress(Integer.parseInt(request.getId()));
    }

    private TSingleResult<Boolean> updateDefaultAddress(Integer addressId) {
        TSingleResult<Boolean> result = ResultBuilder.succTSingle(false);
        if (addressId != null) {
            MemberAddress memberAddress = memberAddressService.getMemberAddress(addressId);
            if (memberAddress != null) {
                memberAddressService.updateDefaultAddress(memberAddress.getMemberId(), memberAddress.getAddressId());
                memberService.updateDefaultAddressId(memberAddress.getMemberId(), memberAddress.getAddressId());
                result.setData(true);
            }
        }
        return result;
    }
}
