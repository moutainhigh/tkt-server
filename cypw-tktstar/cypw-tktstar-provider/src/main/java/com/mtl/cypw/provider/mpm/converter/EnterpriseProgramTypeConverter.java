package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.EnterpriseProgramTypeDTO;
import com.mtl.cypw.mpm.model.EnterpriseProgramType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/20.
 */
@Component
public class EnterpriseProgramTypeConverter {

    public EnterpriseProgramTypeDTO toDto(EnterpriseProgramType pojo) {
        if (pojo == null) {
            return null;
        }
        EnterpriseProgramTypeDTO dto = new EnterpriseProgramTypeDTO();
        dto.setId(pojo.getId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setProgramTypeId(pojo.getProgramTypeId());
        dto.setProgramTypeTitle(pojo.getProgramTypeTitle());
        dto.setSortOrder(pojo.getSortOrder());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setDeleted(pojo.getDeleted());
        return dto;
    }

    public List<EnterpriseProgramTypeDTO> toDto(List<EnterpriseProgramType> list) {
        if (list == null) {
            return null;
        }
        List<EnterpriseProgramTypeDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
