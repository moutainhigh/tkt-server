package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.EnterpriseSearchDTO;
import com.mtl.cypw.mpm.model.EnterpriseSearch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Component
public class EnterpriseSearchConverter {
    public EnterpriseSearchDTO toDto(EnterpriseSearch pojo) {
        if (pojo == null) {
            return null;
        }
        EnterpriseSearchDTO dto = new EnterpriseSearchDTO();
        dto.setId(pojo.getId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setSearchKey(pojo.getSearchKey());
        dto.setGuessKey(pojo.getGuessKey());
        dto.setCreateDate(pojo.getCreateDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setCreateUser(pojo.getCreateUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<EnterpriseSearchDTO> toDto(List<EnterpriseSearch> list) {
        if (list == null) {
            return null;
        }
        List<EnterpriseSearchDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
