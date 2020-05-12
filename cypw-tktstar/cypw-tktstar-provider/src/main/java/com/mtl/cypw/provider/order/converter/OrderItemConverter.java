package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.dto.OrderItemDTO;
import com.mtl.cypw.order.model.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderItemConverter extends DataConverter<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO convert(OrderItem object) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setOrderItemId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setProductTitle(object.getProductTitle());
        dto.setSkuType(SkuTypeEnum.getObject(object.getSkuType()));
        /**
         * skuId
         */
        dto.setPriceId(object.getPriceId());
        dto.setPriceDesc(object.getPriceDesc());
        dto.setImageSrc(object.getImageSrc());
        dto.setItemId(object.getItemId());
        dto.setIsPackage(BooleanUtils.toBooleanObject(object.getIsPackage()));
        dto.setPackageNumber(object.getPackageNumber());
        dto.setOriginPrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setUnitPrice(MoneyUtils.getMoneyByCent(object.getUnitPrice()));
        dto.setCostFee(MoneyUtils.getMoneyByCent(object.getCostFee()));
        dto.setDiscountFee(MoneyUtils.getMoneyByCent(object.getDiscountFee()));
        dto.setQuantity(object.getQuantity());
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;
    }

}
