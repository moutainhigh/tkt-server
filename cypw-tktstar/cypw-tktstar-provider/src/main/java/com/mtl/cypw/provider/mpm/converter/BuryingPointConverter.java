package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.BuryingPointDTO;
import com.mtl.cypw.domain.mpm.enums.BuryingPointTypeEnum;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.mpm.model.BuryingPointInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Component
public class BuryingPointConverter {

    public BuryingPointDTO toDto(BuryingPointInfo pojo) {
        if (pojo == null) {
            return null;
        }
        BuryingPointDTO dto = new BuryingPointDTO();
        dto.setId(pojo.getId());
        dto.setMemberId(pojo.getMemberId());
        dto.setBuryingPointType(BuryingPointTypeEnum.getObject(pojo.getBuryingPointType()));
        dto.setBuryingPointContent(pojo.getBuryingPointContent());
        dto.setSourcePlatform(pojo.getSourcePlatform());
        dto.setSourcePage(pojo.getSourcePage());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setCreateTime(pojo.getCreateTime());
        return dto;
    }

    public List<BuryingPointDTO> toDto(List<BuryingPointInfo> list) {
        if (list == null) {
            return null;
        }
        List<BuryingPointDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public BuryingPointInfo toEntity(BuryingPointParam param) {
        if (param == null) {
            return null;
        }
        BuryingPointInfo pojo = new BuryingPointInfo();
        pojo.setMemberId(param.getMemberId());
        pojo.setBuryingPointType(param.getBuryingPointType().getCode());
        pojo.setBuryingPointContent(param.getBuryingPointContent());
        pojo.setSourcePlatform(param.getSourcePlatform());
        pojo.setSourcePage(param.getSourcePage());
        pojo.setEnterpriseId(param.getEnterpriseId());
        return pojo;
    }
}
