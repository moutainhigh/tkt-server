package com.mtl.cypw.provider.member.converter;

import com.mtl.cypw.domain.member.dto.MemberAddressDTO;
import com.mtl.cypw.domain.member.param.MemberAddressParam;
import com.mtl.cypw.member.pojo.MemberAddress;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Component
public class MemberAddressConverter {
    public MemberAddressDTO toDto(MemberAddress pojo) {
        if (pojo == null) {
            return null;
        }
        MemberAddressDTO dto = new MemberAddressDTO();
        dto.setAddressId(pojo.getAddressId());
        dto.setMemberId(pojo.getMemberId());
        dto.setDeliveryName(pojo.getDeliveryName());
        dto.setDeliveryMobile(pojo.getDeliveryMobile());
        dto.setProvinceCode(pojo.getProvinceCode());
        dto.setProvinceName(pojo.getProvinceName());
        dto.setCityCode(pojo.getCityCode());
        dto.setCityName(pojo.getCityName());
        dto.setDistrictCode(pojo.getDistrictCode());
        dto.setDistrictName(pojo.getDistrictName());
        dto.setDeliveryAddress(pojo.getDeliveryAddress());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setIsDefault(pojo.getIsDefault());
        return dto;
    }

    public List<MemberAddressDTO> toDto(List<MemberAddress> list) {
        if (list == null) {
            return null;
        }
        List<MemberAddressDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public MemberAddress toEntity(MemberAddressParam param) {
        if (param == null) {
            return null;
        }
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddressId(param.getAddressId());
        memberAddress.setMemberId(param.getMemberId());
        memberAddress.setDeliveryName(param.getDeliveryName());
        memberAddress.setDeliveryMobile(param.getDeliveryMobile());
        memberAddress.setProvinceCode(param.getProvinceCode());
        memberAddress.setProvinceName(param.getProvinceName());
        memberAddress.setCityCode(param.getCityCode());
        memberAddress.setCityName(param.getCityName());
        memberAddress.setDistrictCode(param.getDistrictCode());
        memberAddress.setDistrictName(param.getDistrictName());
        memberAddress.setDeliveryAddress(param.getDeliveryAddress());
        memberAddress.setIsDefault(param.getIsDefault());
        return memberAddress;
    }
}
