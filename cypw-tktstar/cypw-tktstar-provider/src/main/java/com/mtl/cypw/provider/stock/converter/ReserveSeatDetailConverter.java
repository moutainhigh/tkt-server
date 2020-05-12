package com.mtl.cypw.provider.stock.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.stock.dto.ReserveSeatDetailDTO;
import com.mtl.cypw.stock.model.ReserveSeatDetail;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 17:46
 */
@Component
public class ReserveSeatDetailConverter extends DataConverter<ReserveSeatDetail, ReserveSeatDetailDTO> {

    @Override
    public ReserveSeatDetailDTO convert(ReserveSeatDetail input) {
        ReserveSeatDetailDTO dto = new ReserveSeatDetailDTO();
        dto.setSeatDetailId(input.getId());
        dto.setReserveId(input.getReserveId());
        dto.setEventId(input.getEventId());
        dto.setStatus(input.getStatus());
        dto.setZoneId(input.getZoneId());
        dto.setZoneName(input.getZoneName());
        dto.setPriceId(input.getPriceId());
        dto.setPriceValue(new Money(input.getPriceValue()));
        dto.setSeatId(input.getSeatId());
        dto.setSeatName(input.getSeatName());
        dto.setEnterpriseId(input.getEnterpriseId());
        return dto;
    }
}
