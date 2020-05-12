package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.MachinePosterDTO;
import com.mtl.cypw.mpm.model.MachinePoster;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/16.
 */
@Component
public class MachinePosterConverter {

    public MachinePosterDTO toDto(MachinePoster pojo) {
        if (pojo == null) {
            return null;
        }
        MachinePosterDTO dto = new MachinePosterDTO();
        dto.setPosterId(pojo.getPosterId());
        dto.setPosterName(pojo.getPosterName());
        dto.setPosterImage(pojo.getPosterImage());
        dto.setSortOrder(pojo.getSortOrder());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setIsDelete(pojo.getIsDelete());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setCreateDate(pojo.getCreateDate());
        dto.setCreateUser(pojo.getCreateUser());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<MachinePosterDTO> toDto(List<MachinePoster> list) {
        if (list == null) {
            return null;
        }
        List<MachinePosterDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
