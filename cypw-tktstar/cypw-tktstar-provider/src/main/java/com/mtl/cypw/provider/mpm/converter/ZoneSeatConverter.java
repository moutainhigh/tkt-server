package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.ZoneSeatDTO;
import com.mtl.cypw.mpm.model.ZoneSeat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Component
public class ZoneSeatConverter {
    public ZoneSeatDTO toDto(ZoneSeat pojo) {
        if (pojo == null) {
            return null;
        }
        ZoneSeatDTO dto = new ZoneSeatDTO();
        dto.setZoneSeatId(pojo.getZoneSeatId());
        dto.setZoneId(pojo.getZoneId());
        dto.setRowName(pojo.getRowName());
        dto.setColName(pojo.getColName());
        dto.setRowInt(pojo.getRowInt());
        dto.setColInt(pojo.getColInt());
        dto.setOnlyPrefix(pojo.getOnlyPrefix());
        dto.setSeatType(pojo.getSeatType());
        dto.setAddDate(pojo.getAddDate());
        dto.setAddUser(pojo.getAddUser());
        return dto;
    }

    public List<ZoneSeatDTO> toDto(List<ZoneSeat> list) {
        if (list == null) {
            return null;
        }
        List<ZoneSeatDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
