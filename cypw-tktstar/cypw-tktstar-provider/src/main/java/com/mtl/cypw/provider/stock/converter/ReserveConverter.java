package com.mtl.cypw.provider.stock.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.stock.dto.ReserveFormDTO;
import com.mtl.cypw.stock.model.ReserveForm;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 17:46
 */
@Component
public class ReserveConverter extends DataConverter<ReserveForm, ReserveFormDTO> {

    @Override
    public ReserveFormDTO convert(ReserveForm input) {
        if (input == null) {
            return null;
        }
        ReserveFormDTO dto = new ReserveFormDTO();
        dto.setReserveId(input.getId());
        dto.setProgramId(input.getProgramId());
        dto.setEventId(input.getEventId());
        dto.setReservedStatus(input.getReservedStatus());
        dto.setAutoRelease(input.getAutoRelease());
        dto.setReleaseTime(input.getReleaseTime());
        dto.setEnterpriseId(input.getEnterpriseId());
        dto.setReservedName(input.getReservedName());
        dto.setReservedNo(input.getReservedNo());
        dto.setReservedTime(input.getReservedTime());
        dto.setTargetMobile(input.getTargetMobile());
        dto.setTargetName(input.getTargetName());
        dto.setSeatCount(input.getSeatCount());
        dto.setTotalAmount(new Money(input.getTotalAmount()));
        dto.setMemberId(input.getMemberId());
        dto.setRemark(input.getRemark());
        return dto;
    }
}
