package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.ZoneDTO;
import com.mtl.cypw.mpm.model.Zone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Component
public class ZoneConverter {
    public ZoneDTO toDto(Zone pojo) {
        if (pojo == null) {
            return null;
        }
        ZoneDTO dto = new ZoneDTO();
        dto.setZoneId(pojo.getZoneId());
        dto.setTemplateId(pojo.getTemplateId());
        dto.setSvgId(pojo.getSvgId());
        dto.setZoneName(pojo.getZoneName());
        dto.setZoneEntrance(pojo.getZoneEntrance());
        dto.setZoneRemark(pojo.getZoneRemark());
        dto.setBeginX(pojo.getBeginX());
        dto.setBeginY(pojo.getBeginY());
        dto.setSeatHeight(pojo.getSeatHeight());
        dto.setSeatWidth(pojo.getSeatWidth());
        dto.setSeatMargin(pojo.getSeatMargin());
        return dto;
    }

    public List<ZoneDTO> toDto(List<Zone> list) {
        if (list == null) {
            return null;
        }
        List<ZoneDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
