package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.common.dto.EntityMapper;
import com.mtl.cypw.domain.show.dto.ProgramDistributionDTO;
import com.mtl.cypw.show.pojo.ProgramDistribution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@Component
public class ProgramDistributionConverter implements EntityMapper<ProgramDistributionDTO, ProgramDistribution> {
    @Override
    public ProgramDistribution toEntity(ProgramDistributionDTO programDistributionDTO) {
        return null;
    }

    @Override
    public ProgramDistributionDTO toDto(ProgramDistribution programDistribution) {
        if (programDistribution == null) {
            return null;
        }
        ProgramDistributionDTO dto = new ProgramDistributionDTO();
        dto.setDistributionId(programDistribution.getDistributionId());
        dto.setProgramId(programDistribution.getProgramId());
        dto.setDistributorId(programDistribution.getDistributorId());
        dto.setDistributionCode(programDistribution.getDistributionCode());
        dto.setDistributionRate(programDistribution.getDistributionRate());
        dto.setBeginDate(programDistribution.getBeginDate());
        dto.setEndDate(programDistribution.getEndDate());
        dto.setIsEnable(programDistribution.getIsEnable());
        dto.setDistributionDesc(programDistribution.getDistributionDesc());
        dto.setAddDate(programDistribution.getAddDate());
        dto.setUpdateDate(programDistribution.getUpdateDate());
        dto.setAddUser(programDistribution.getAddUser());
        dto.setUpdateUser(programDistribution.getUpdateUser());
        return dto;
    }

    @Override
    public List<ProgramDistribution> toEntity(Collection<ProgramDistributionDTO> collection) {
        return null;
    }

    @Override
    public List<ProgramDistributionDTO> toDto(Collection<ProgramDistribution> collection) {
        if (collection == null) {
            return null;
        }
        List<ProgramDistributionDTO> list = new ArrayList<>();
        collection.forEach(n -> list.add(toDto(n)));
        return list;
    }
}
