package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.VenueDTO;
import com.mtl.cypw.mpm.model.Venue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Component
public class VenueConverter {
    public VenueDTO toDto(Venue pojo) {
        if (pojo == null) {
            return null;
        }
        VenueDTO dto = new VenueDTO();
        dto.setVenueId(pojo.getVenueId());
        dto.setTheatreId(pojo.getTheatreId());
        dto.setVenueName(pojo.getVenueName());
        dto.setVenueDesc(pojo.getVenueDesc());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setIsEnable(pojo.getIsEnable());
        return dto;
    }

    public List<VenueDTO> toDto(List<Venue> list) {
        if (list == null) {
            return null;
        }
        List<VenueDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
