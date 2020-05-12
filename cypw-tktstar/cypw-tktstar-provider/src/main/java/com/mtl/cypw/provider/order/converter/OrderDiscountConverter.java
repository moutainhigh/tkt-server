package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.domain.order.dto.OrderDiscountDetailDTO;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderDiscountConverter extends DataConverter<OrderDiscountDetail, OrderDiscountDetailDTO> {

    @Override
    public OrderDiscountDetailDTO convert(OrderDiscountDetail object) {
        if (object == null) {
            return null;
        }
        OrderDiscountDetailDTO dto = new OrderDiscountDetailDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setDiscountDetailId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setDiscountType(PromotionTypeEnum.getObject(object.getDiscountType()));
        dto.setDiscountAmount(MoneyUtils.getMoneyByCent(object.getDiscountAmount()));
        dto.setDiscountNumber(object.getDiscountNumber());
        dto.setExchangeSkuId(object.getExchangeSkuId());
        dto.setCouponId(object.getCouponId());
        dto.setCouponCode(object.getCouponCode());
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;
    }

}
