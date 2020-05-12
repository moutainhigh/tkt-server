package com.mtl.cypw.provider.coupon.converter;

import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.domain.coupon.dto.PromotionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Component
public class PromotionConverter {

    public PromotionDTO toDto(Promotion pojo) {
        if (pojo == null) {
            return null;
        }
        PromotionDTO dto = new PromotionDTO();
        dto.setPromotionId(pojo.getPromotionId());
        dto.setBusinessTypeId(pojo.getBusinessTypeId());
        dto.setPromotionTypeId(pojo.getPromotionTypeId());
        dto.setPromotionName(pojo.getPromotionName());
        dto.setProgramPointId(pojo.getProgramPointId());
        dto.setProgramTypeIds(pojo.getProgramTypeIds());
        dto.setOrderAmountRestriction(pojo.getOrderAmountRestriction());
        dto.setMinQtyRestriction(pojo.getMinQtyRestriction());
        dto.setMaxQtyRestriction(pojo.getMaxQtyRestriction());
        dto.setMaxQtyRestrictionPerEvent(pojo.getMaxQtyRestrictionPerEvent());
        dto.setMemberRestriction(pojo.getMemberRestriction());
        dto.setPaymentRestriction(pojo.getPaymentRestriction());
        dto.setAppShow(pojo.getAppShow());
        dto.setWechatShow(pojo.getWechatShow());
        dto.setPcShow(pojo.getPcShow());
        dto.setPromotionBeginDate(pojo.getPromotionBeginDate());
        dto.setPromotionEndDate(pojo.getPromotionEndDate());
        dto.setIsDisplay(pojo.getIsDisplay());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setIsDelete(pojo.getIsDelete());
        dto.setListImage(pojo.getListImage());
        dto.setDetailImage(pojo.getDetailImage());
        dto.setPromotionBrief(pojo.getPromotionBrief());
        dto.setPromotionContent(pojo.getPromotionContent());
        dto.setPromotionRemark(pojo.getPromotionRemark());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setQrCode(pojo.getQrCode());
        dto.setKeyType(pojo.getKeyType());
        dto.setPromotionDiscount(pojo.getPromotionDiscount());
        dto.setPromotionAmount(pojo.getPromotionAmount());
        dto.setPromotionQtyRate(pojo.getPromotionQtyRate());
        dto.setPromotionGiftPriceId(pojo.getPromotionGiftPriceId());
        dto.setShowTimeImage(pojo.getShowTimeImage());
        dto.setShowOtherImages(pojo.getShowOtherImages());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setLimitDateString(pojo.getLimitDateString());
        dto.setPromotionCode(pojo.getPromotionCode());
        dto.setAllowExchangeQty(pojo.getAllowExchangeQty());
        dto.setHaveExchangeQty(pojo.getHaveExchangeQty());
        dto.setDistributionType(pojo.getDistributionType());
        dto.setAvailableDay(pojo.getAvailableDay());
        dto.setAvailableBeginDate(pojo.getAvailableBeginDate());
        dto.setAvailableEndDate(pojo.getAvailableEndDate());
        return dto;
    }

    public List<PromotionDTO> toDto(List<Promotion> list) {
        if (list == null) {
            return null;
        }
        List<PromotionDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
