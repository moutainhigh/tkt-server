package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.DistrictDTO;
import com.mtl.cypw.mpm.model.District;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang
 * @date 2019-11-27.
 */
@Component
public class DistrictConverter {

    public DistrictDTO toDto(District pojo) {
        if (pojo == null) {
            return null;
        }
        DistrictDTO dto = new DistrictDTO();
        dto.setDistrictCode(pojo.getDistrictCode());
        dto.setCityCode(pojo.getCityCode());
        dto.setDistrictBriefName(pojo.getDistrictBriefName());
        dto.setDistrictName(pojo.getDistrictName());
        return dto;
    }

    public List<DistrictDTO> toDto(List<District> list) {
        if (list == null) {
            return null;
        }
        List<DistrictDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
