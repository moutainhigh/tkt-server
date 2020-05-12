package com.mtl.cypw.provider.member.converter;

import com.mtl.cypw.domain.member.dto.MemberDTO;
import com.mtl.cypw.domain.member.param.MemberParam;
import com.mtl.cypw.member.pojo.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Component
public class MemberConverter {
    public MemberDTO toDto(Member pojo) {
        if (pojo == null) {
            return null;
        }
        MemberDTO dto = new MemberDTO();
        dto.setMemberId(pojo.getMemberId());
        dto.setMemberName(pojo.getMemberName());
        dto.setNickName(pojo.getNickName());
        dto.setMemberMobile(pojo.getMemberMobile());
        dto.setMemberDesc(pojo.getMemberDesc());
        dto.setMemberPass(pojo.getMemberPass());
        dto.setTempPass(pojo.getTempPass());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setFirstOrderDate(pojo.getFirstOrderDate());
        dto.setMemberLevelId(pojo.getMemberLevelId());
        dto.setIsBlacklist(pojo.getIsBlacklist());
        dto.setKeyaccountId(pojo.getKeyaccountId());
        dto.setCustomerTagIds(pojo.getCustomerTagIds());
        dto.setMemberMileage(pojo.getMemberMileage());
        dto.setMemberDeposit(pojo.getMemberDeposit());
        dto.setOpenId(pojo.getOpenId());
        dto.setMemberToken(pojo.getMemberToken());
        dto.setTokenDate(pojo.getTokenDate());
        dto.setTokenDesc(pojo.getTokenDesc());
        dto.setIsDefault(pojo.getIsDefault());
        dto.setParentId(pojo.getParentId());
        dto.setCompanyName(pojo.getCompanyName());
        dto.setContactName(pojo.getContactName());
        dto.setContactDesc(pojo.getContactDesc());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setMemberEmail(pojo.getMemberEmail());
        dto.setMemberImage(pojo.getMemberImage());
        dto.setDefaultAddressId(pojo.getDefaultAddressId());
        dto.setThirdPartyUserId(pojo.getThirdPartyUserId());
        return dto;
    }

    public List<MemberDTO> toDto(List<Member> list) {
        if (list == null) {
            return null;
        }
        List<MemberDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public Member toEntity(MemberParam param) {
        if (param == null) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(param.getMemberId());
        member.setMemberName(param.getMemberName());
        member.setNickName(param.getNickName());
        return member;
    }
}
