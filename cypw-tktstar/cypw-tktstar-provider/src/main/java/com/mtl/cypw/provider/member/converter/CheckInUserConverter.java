package com.mtl.cypw.provider.member.converter;

import com.mtl.cypw.domain.member.dto.CheckInUserDTO;
import com.mtl.cypw.domain.member.param.CheckInUserParam;
import com.mtl.cypw.member.pojo.CheckInUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@Component
public class CheckInUserConverter {

    public CheckInUserDTO toDto(CheckInUser pojo) {
        if (pojo == null) {
            return null;
        }
        CheckInUserDTO dto = new CheckInUserDTO();
        dto.setId(pojo.getId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setUserName(pojo.getUserName());
        dto.setUserPass(pojo.getUserPass());
        dto.setPersonName(pojo.getPersonName());
        dto.setBeginDate(pojo.getBeginDate());
        dto.setEndDate(pojo.getEndDate());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setRemark(pojo.getRemark());
        dto.setAddDate(pojo.getAddDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<CheckInUserDTO> toDto(List<CheckInUser> list) {
        if (list == null) {
            return null;
        }
        List<CheckInUserDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public CheckInUser toEntity(CheckInUserParam param) {
        if (param == null) {
            return null;
        }
        CheckInUser pojo = new CheckInUser();
        pojo.setEnterpriseId(param.getEnterpriseId());
        pojo.setUserName(param.getUserName());
        pojo.setUserPass(param.getUserPass());
        pojo.setPersonName(param.getPersonName());
        pojo.setBeginDate(param.getBeginDate());
        pojo.setEndDate(param.getEndDate());
        pojo.setIsEnable(param.getIsEnable());
        pojo.setRemark(param.getRemark());
        pojo.setAddUser(param.getAddUser());
        pojo.setUpdateUser(param.getUpdateUser());
        return pojo;
    }
}
