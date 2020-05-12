package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.EventDTO;
import com.mtl.cypw.show.pojo.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Component
public class EventConverter {

    public EventDTO toDto(Event event) {
        if (event == null) {
            return null;
        }
        EventDTO dto = new EventDTO();
        dto.setEventId(event.getEventId());
        dto.setProgramId(event.getProgramId());
        dto.setEventTitle(event.getEventTitle());
        dto.setEventDate(event.getEventDate());
        dto.setAddDate(event.getAddDate());
        dto.setUpdateDate(event.getUpdateDate());
        dto.setIsSeat(event.getIsSeat());
        dto.setIsEnable(event.getIsEnable());
        dto.setIsDelete(event.getIsDelete());
        dto.setAddUser(event.getAddUser());
        dto.setUpdateUser(event.getUpdateUser());
        dto.setSaleDateBegin(event.getSaleDateBegin());
        dto.setSaleDateEnd(event.getSaleDateEnd());
        dto.setTemplateId(event.getTemplateId());
        dto.setEnterpriseId(event.getEnterpriseId());
        return dto;
    }

    public List<EventDTO> toDto(List<Event> list) {
        if (list == null) {
            return null;
        }
        List<EventDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

}
