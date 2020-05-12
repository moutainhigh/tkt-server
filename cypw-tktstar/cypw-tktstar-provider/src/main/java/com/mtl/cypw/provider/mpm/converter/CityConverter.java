package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.CityDTO;
import com.mtl.cypw.mpm.model.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang
 * @date 2019-11-27.
 */
@Component
public class CityConverter {
    public CityDTO toDto(City pojo) {
        if (pojo == null) {
            return null;
        }
        CityDTO dto = new CityDTO();
        dto.setCityCode(pojo.getCityCode());
        dto.setProvinceCode(pojo.getProvinceCode());
        dto.setCityEname(pojo.getCityEname());
        dto.setCityName(pojo.getCityName());
        return dto;
    }

    public List<CityDTO> toDto(List<City> list) {
        if (list == null) {
            return null;
        }
        List<CityDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
