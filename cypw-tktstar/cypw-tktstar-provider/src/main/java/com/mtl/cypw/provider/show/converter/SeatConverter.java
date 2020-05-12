package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.SeatDTO;
import com.mtl.cypw.show.pojo.EventSeat;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-09 15:39
 */
@Component
public class SeatConverter {

    public SeatDTO toDto(EventSeat seat) {
        if (seat == null) {
            return null;
        }
        SeatDTO dto = new SeatDTO();
        dto.setSeatId(seat.getSeatId());
        dto.setEventId(seat.getEventId());
        dto.setZoneId(seat.getZoneId());
        dto.setPriceId(seat.getPriceId());
        dto.setBgColor(seat.getBgColor());
        dto.setRowInt(seat.getRowInt());
        dto.setRowName(seat.getRowName());
        dto.setColInt(seat.getColInt());
        dto.setColName(seat.getColName());
        dto.setOnlyPrefix(seat.getOnlyPrefix());
        dto.setSeatType(seat.getSeatType());
        dto.setSeatStatus(seat.getSeatStatus());
        dto.setLockId(seat.getLockId());
        dto.setLockDate(seat.getLockDate());
        dto.setPoolId(seat.getPoolId());
        dto.setReserveId(seat.getReserveId());
        dto.setReserveDate(seat.getReserveDate());
        dto.setTicketSn(seat.getTicketSn());
        return dto;
    }

    public List<SeatDTO> toDto(List<EventSeat> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<SeatDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
