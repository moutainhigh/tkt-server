package com.mtl.cypw.provider.order.service.uniform;

import com.google.common.collect.Maps;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.model.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 20:12
 */
@Slf4j
@Component
public class SpecialAssemblyService {


    /**
     *
     * @param order
     * @param orderItems
     * @return
     */
    public Map<Integer, BigDecimal> assemblyDiscount(Order order, List<OrderItem> orderItems) {
        Map<Integer, BigDecimal> skuItemDiscountMap = Maps.newHashMap();
        Map<Integer, OrderItem> skuItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem ::getPriceId, e -> e));
        List<OrderDiscountDetail> discountDetails = order.getDiscountDetails();
        if (CollectionUtils.isEmpty(discountDetails)) {
            return skuItemDiscountMap;
        }
        Map<Integer, List<OrderDiscountDetail>> promotionTypeGroupMap = discountDetails.stream().collect(
                Collectors.groupingBy(OrderDiscountDetail :: getDiscountType));
        for (Map.Entry<Integer, List<OrderDiscountDetail>> entry : promotionTypeGroupMap.entrySet()) {
            if (PromotionTypeEnum.DISCOUNT_COUPON.getCode() == entry.getKey()
                    || PromotionTypeEnum.CASH_COUPON.getCode() == entry.getKey()) {
                OrderDiscountDetail discountDetail = entry.getValue().get(0);

            }
            for (OrderDiscountDetail discountDetail : entry.getValue()) {
                OrderItem orderItem = skuItemMap.get(discountDetail.getExchangeSkuId());
                if (orderItem == null) {
                    continue;
                }
                BigDecimal discountCent = skuItemDiscountMap.get(orderItem.getPriceId());
                if (discountCent != null) {
                    discountCent.add(BigDecimal.valueOf(discountDetail.getDiscountAmount()));
                } else {
                    discountCent = BigDecimal.valueOf(discountDetail.getDiscountAmount());
                }
                skuItemDiscountMap.put(orderItem.getPriceId(), discountCent);
            }
        }
        return skuItemDiscountMap;
    }
}
