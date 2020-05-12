package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.ActivityDTO;
import com.mtl.cypw.show.pojo.Activity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@Component
public class ActivityConverter {

    public ActivityDTO toDto(Activity pojo) {
        if (pojo == null) {
            return null;
        }
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityId(pojo.getActivityId());
        dto.setActivityName(pojo.getActivityName());
        dto.setActivityBrief(pojo.getActivityBrief());
        dto.setActivityImage(pojo.getActivityImage());
        dto.setSortOrder(pojo.getSortOrder());
        dto.setBeginDate(pojo.getBeginDate());
        dto.setEndDate(pojo.getEndDate());
        dto.setTypeId(pojo.getTypeId());
        dto.setActivityUrl(pojo.getActivityUrl());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setAddDate(pojo.getAddDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setIsDelete(pojo.getIsDelete());
        dto.setActivityContent(pojo.getActivityContent());
        return dto;
    }

    public List<ActivityDTO> toDto(List<Activity> list) {
        if (list == null) {
            return null;
        }
        List<ActivityDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
