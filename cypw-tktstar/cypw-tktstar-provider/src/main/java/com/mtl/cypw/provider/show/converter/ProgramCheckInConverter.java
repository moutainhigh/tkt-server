package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.common.dto.EntityMapper;
import com.mtl.cypw.domain.show.dto.ProgramCheckInDTO;
import com.mtl.cypw.show.pojo.ProgramCheckIn;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author sq
 * @date 2020/3/17  18:40
 */

@Component
public class ProgramCheckInConverter implements EntityMapper<ProgramCheckInDTO, ProgramCheckIn> {
    @Override
    public ProgramCheckIn toEntity(ProgramCheckInDTO dto) {
        return null;
    }

    @Override
    public ProgramCheckInDTO toDto(ProgramCheckIn entity) {
        if (entity == null) {
            return null;
        }
        ProgramCheckInDTO programCheckInDTO = new ProgramCheckInDTO();
        programCheckInDTO.setId(entity.getId());
        programCheckInDTO.setProgramId(entity.getProgramId());
        programCheckInDTO.setEntranceName(entity.getEntranceName());
        programCheckInDTO.setCheckinCount(entity.getCheckinCount());
        programCheckInDTO.setCheckinAccountIds(entity.getCheckinAccountIds());
        return programCheckInDTO;


    }

    @Override
    public Collection<ProgramCheckIn> toEntity(Collection<ProgramCheckInDTO> dtoList) {
        return null;
    }

    @Override
    public Collection<ProgramCheckInDTO> toDto(Collection<ProgramCheckIn> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ProgramCheckInDTO> dtoList = Lists.newArrayList();
        entityList.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
