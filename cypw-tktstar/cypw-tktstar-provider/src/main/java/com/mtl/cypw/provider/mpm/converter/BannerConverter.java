package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.BannerDTO;
import com.mtl.cypw.mpm.model.Banner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 12:46
 */
@Component
public class BannerConverter {

    public BannerDTO toDto(Banner pojo) {
        if (pojo == null) {
            return null;
        }
        BannerDTO dto = new BannerDTO();
        dto.setBannerId(pojo.getBannerId());
        dto.setBannerName(pojo.getBannerName());
        dto.setBannerDesc(pojo.getBannerDesc());
        dto.setBannerImage(pojo.getBannerImage());
        dto.setBannerType(pojo.getBannerType());
        dto.setLinkType(pojo.getLinkType());
        dto.setResourceId(pojo.getResourceId());
        dto.setBannerUrl(pojo.getBannerUrl());
        dto.setBeginDate(pojo.getBeginDate());
        dto.setEndDate(pojo.getEndDate());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setSortOrder(pojo.getSortOrder());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<BannerDTO> toDto(List<Banner> list) {
        if (list == null) {
            return null;
        }
        List<BannerDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
