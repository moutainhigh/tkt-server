package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.EnterpriseDialogDTO;
import com.mtl.cypw.mpm.model.EnterpriseDialog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/02/18.
 */
@Component
public class EnterpriseDialogConverter {

    public EnterpriseDialogDTO toDto(EnterpriseDialog pojo) {
        if (pojo == null) {
            return null;
        }
        EnterpriseDialogDTO dto = new EnterpriseDialogDTO();
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setMessage1(pojo.getMessage1());
        dto.setMessage2(pojo.getMessage2());
        dto.setIsShow(pojo.getIsShow());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<EnterpriseDialogDTO> toDto(List<EnterpriseDialog> list) {
        if (list == null) {
            return null;
        }
        List<EnterpriseDialogDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
