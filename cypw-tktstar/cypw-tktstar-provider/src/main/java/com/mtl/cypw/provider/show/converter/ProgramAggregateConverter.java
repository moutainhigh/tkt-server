package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.common.dto.EntityMapper;
import com.mtl.cypw.domain.show.dto.aggregate.ProgramAggregateDTO;
import com.mtl.cypw.show.pojo.aggregate.ProgramAggregate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author sq
 * @date 2020/3/17  11:17
 */
@Component
public class ProgramAggregateConverter implements EntityMapper<ProgramAggregateDTO, ProgramAggregate> {


    @Override
    public ProgramAggregate toEntity(ProgramAggregateDTO dto) {
        return null;
    }

    @Override
    public ProgramAggregateDTO toDto(ProgramAggregate programAggregate) {
        if (programAggregate == null) {
            return null;
        }
        ProgramAggregateDTO dto = new ProgramAggregateDTO();
        dto.setProgramId(programAggregate.getProgramId());
        dto.setProgramNo(programAggregate.getProgramNo());
        dto.setProgramTitleBrief(programAggregate.getProgramTitleBrief());
        dto.setTheatreAndVenueName(programAggregate.getTheatreName() + '-' + programAggregate.getVenueName());
        dto.setProgramPrice(programAggregate.getProgramPrice());
        dto.setSaleDateBegin(programAggregate.getSaleDateBegin());
        dto.setSaleDateEnd(programAggregate.getSaleDateEnd());
        if (programAggregate.getWechatShow() == 1) {
            dto.setSaleStatusName(programAggregate.getSaleStatusName() + "(公开)");
        } else {
            dto.setSaleStatusName(programAggregate.getSaleStatusName() + "(内部)");
        }

        dto.setEventCnt(programAggregate.getEventCnt());
        dto.setComboTicketCnt(programAggregate.getComboTicketCnt());
        return dto;
    }

    @Override
    public Collection<ProgramAggregate> toEntity(Collection<ProgramAggregateDTO> dtoList) {
        return null;
    }

    @Override
    public Collection<ProgramAggregateDTO> toDto(Collection<ProgramAggregate> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ProgramAggregateDTO> list = new ArrayList<>();
        entityList.forEach(n -> list.add(toDto(n)));
        return list;
    }

}
