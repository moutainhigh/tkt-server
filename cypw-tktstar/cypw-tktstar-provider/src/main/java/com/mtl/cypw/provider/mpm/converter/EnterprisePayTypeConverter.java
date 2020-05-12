package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.EnterprisePayTypeDTO;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/20.
 */
@Component
public class EnterprisePayTypeConverter {

    public EnterprisePayTypeDTO toDto(EnterprisePayType pojo) {
        if (pojo == null) {
            return null;
        }
        EnterprisePayTypeDTO dto = new EnterprisePayTypeDTO();
        dto.setEnterprisePayTypeId(pojo.getEnterprisePayTypeId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setPayTypeId(pojo.getPayTypeId());
        dto.setPayTypeName(pojo.getPayTypeName());
        dto.setConfigJson(pojo.getConfigJson());
        dto.setRemark(pojo.getRemark());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setTransactionFeeRate(pojo.getTransactionFeeRate());
        return dto;
    }

    public List<EnterprisePayTypeDTO> toDto(List<EnterprisePayType> list) {
        if (list == null) {
            return null;
        }
        List<EnterprisePayTypeDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
