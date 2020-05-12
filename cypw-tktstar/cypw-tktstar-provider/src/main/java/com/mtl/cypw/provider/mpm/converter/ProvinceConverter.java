package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.ProvinceDTO;
import com.mtl.cypw.mpm.model.Province;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang
 * @date 2019-11-27.
 */
@Component
public class ProvinceConverter {

    public ProvinceDTO toDto(Province pojo) {
        if (pojo == null) {
            return null;
        }
        ProvinceDTO dto = new ProvinceDTO();
        dto.setProvinceCode(pojo.getProvinceCode());
        dto.setProvinceName(pojo.getProvinceName());
        dto.setProvinceEname(pojo.getProvinceEname());
        dto.setNationcode(pojo.getNationcode());
        dto.setNationname(pojo.getNationname());
        dto.setNationename(pojo.getNationename());
        dto.setContinentcode(pojo.getContinentcode());
        dto.setContinentname(pojo.getContinentname());
        dto.setContinentename(pojo.getContinentename());
        dto.setRegionlevel(pojo.getRegionlevel());
        return dto;
    }

    public List<ProvinceDTO> toDto(List<Province> list) {
        if (list == null) {
            return null;
        }
        List<ProvinceDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
