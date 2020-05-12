package com.mtl.cypw.provider.coupon.converter;

import com.mtl.cypw.coupon.pojo.PromotionCoupon;
import com.mtl.cypw.domain.coupon.dto.PromotionCouponDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Component
public class PromotionCouponConverter {

    public PromotionCouponDTO toDto(PromotionCoupon pojo) {
        if (pojo == null) {
            return null;
        }
        PromotionCouponDTO dto = new PromotionCouponDTO();
        dto.setCouponId(pojo.getCouponId());
        dto.setPromotionId(pojo.getPromotionId());
        dto.setCouponCode(pojo.getCouponCode());
        dto.setCouponPass(pojo.getCouponPass());
        dto.setIsUsed(pojo.getIsUsed());
        dto.setOrderNo(pojo.getOrderNo());
        dto.setUseDate(pojo.getUseDate());
        dto.setIsBinded(pojo.getIsBinded());
        dto.setMemberId(pojo.getMemberId());
        dto.setBindDate(pojo.getBindDate());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setAddDate(pojo.getAddDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setBeginDate(pojo.getBeginDate());
        dto.setEndDate(pojo.getEndDate());
        dto.setUseLog(pojo.getUseLog());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        return dto;
    }

    public List<PromotionCouponDTO> toDto(List<PromotionCoupon> list) {
        if (list == null) {
            return null;
        }
        List<PromotionCouponDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
