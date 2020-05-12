package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.TheatreDTO;
import com.mtl.cypw.mpm.model.Theatre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Component
public class TheatreConverter {

    public TheatreDTO toDto(Theatre pojo) {
        if (pojo == null) {
            return null;
        }
        TheatreDTO dto = new TheatreDTO();
        dto.setTheatreId(pojo.getTheatreId());
        dto.setTheatreName(pojo.getTheatreName());
        dto.setTheatreAddress(pojo.getTheatreAddress());
        dto.setTheatreContent(pojo.getTheatreContent());
        dto.setCityCode(pojo.getCityCode());
        dto.setTheatreImage(pojo.getTheatreImage());
        dto.setPrepaymentAmount(pojo.getPrepaymentAmount());
        dto.setCreditAmount(pojo.getCreditAmount());
        dto.setLongitudeAndLatitude(pojo.getLongitudeAndLatitude());
        dto.setTheatreName(pojo.getTheatreName());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setIsEnable(pojo.getIsEnable());
        return dto;
    }

    public List<TheatreDTO> toDto(List<Theatre> list) {
        if (list == null) {
            return null;
        }
        List<TheatreDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
