package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.TemplateDTO;
import com.mtl.cypw.mpm.model.Template;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Component
public class TemplateConverter {

    public TemplateDTO toDto(Template pojo) {
        if (pojo == null) {
            return null;
        }
        TemplateDTO dto = new TemplateDTO();
        dto.setTemplateId(pojo.getTemplateId());
        dto.setVenueId(pojo.getVenueId());
        dto.setTemplateName(pojo.getTemplateName());
        dto.setTemplateDesc(pojo.getTemplateDesc());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setTemplateMap(pojo.getTemplateMap());
        dto.setOneSvg(pojo.getOneSvg());
        dto.setMapTypeId(pojo.getMapTypeId());
        return dto;
    }

    public List<TemplateDTO> toDto(List<Template> list) {
        if (list == null) {
            return null;
        }
        List<TemplateDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
