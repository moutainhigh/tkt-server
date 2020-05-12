package com.mtl.cypw.provider.member.converter;

import com.mtl.cypw.domain.member.dto.MemberSignInDTO;
import com.mtl.cypw.domain.member.param.MemberSignInParam;
import com.mtl.cypw.member.pojo.MemberSignIn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Component
public class MemberSignInConverter {

    public MemberSignInDTO toDto(MemberSignIn pojo) {
        if (pojo == null) {
            return null;
        }
        MemberSignInDTO dto = new MemberSignInDTO();
        dto.setId(pojo.getId());
        dto.setMemberId(pojo.getMemberId());
        dto.setOrderId(pojo.getOrderId());
        dto.setProgramId(pojo.getProgramId());
        dto.setEventId(pojo.getEventId());
        dto.setTheatreId(pojo.getTheatreId());
        dto.setSignInAddress(pojo.getSignInAddress());
        dto.setLongitude(pojo.getLongitude());
        dto.setLatitude(pojo.getLatitude());
        dto.setType(pojo.getType());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setRemark(pojo.getRemark());
        dto.setSignInTime(pojo.getCreateTime());
        return dto;
    }

    public List<MemberSignInDTO> toDto(List<MemberSignIn> list) {
        if (list == null) {
            return null;
        }
        List<MemberSignInDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public MemberSignIn toEntity(MemberSignInParam param) {
        if (param == null) {
            return null;
        }
        MemberSignIn pojo = new MemberSignIn();
        pojo.setMemberId(param.getMemberId());
        pojo.setOrderId(param.getOrderId());
        pojo.setProgramId(param.getProgramId());
        pojo.setEventId(param.getEventId());
        pojo.setTheatreId(param.getTheatreId());
        pojo.setSignInAddress(param.getSignInAddress());
        pojo.setLongitude(param.getLongitude());
        pojo.setLatitude(param.getLatitude());
        if (param.getType() != null) {
            pojo.setType(param.getType());
        } else {
            pojo.setType(1);
        }
        pojo.setEnterpriseId(param.getEnterpriseId());
        pojo.setRemark(param.getRemark());
        return pojo;
    }
}
