package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.param.UniformSkuParam;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.mall.service.MerchandiseService;
import com.mtl.cypw.order.exception.init.TicketStatusException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderItem;
import com.mtl.cypw.provider.order.service.uniform.SkuService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-08 14:02
 */
@Slf4j
@Component("goodsSkuService")
public class GoodsSkuServiceImpl implements SkuService {

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Override
    public OrderItem buildOrderItem(Order order, UniformSkuParam param) {
        log.info("build goods order items, param:{}", JsonUtils.toJson(param));
        Assert.notNull(order, "order is null");
        Assert.notNull(param, "sku is null");
        Assert.isTrue(SkuTypeEnum.GOODS.getCode() == param.getType().getCode(), "sku error");
        EventPrice eventPrice = eventPriceService.getEventPriceById(param.getSkuId());
        if (eventPrice == null) {
            log.error("不存在的商品信息, param:{}", JsonUtils.toJson(param));
            throw new TicketStatusException("不存在的商品信息");
        }
        Merchandise merchandise = merchandiseService.get(eventPrice.getEventId());
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(merchandise.getMerchandiseId());
        orderItem.setProductTitle(merchandise.getMerchandiseName());
        orderItem.setEnterpriseId(merchandise.getEnterpriseId());
        orderItem.setIsPackage(0);
        orderItem.setPackageNumber(1);
        orderItem.setSkuType(SkuTypeEnum.GOODS.getCode());
        orderItem.setPriceId(param.getSkuId());
        orderItem.setPriceDesc(eventPrice.getPriceTitle());
        orderItem.setImageSrc(merchandise.getMerchandiseImage());
        int count = param.getCount();
        long unitPrice = new Money(eventPrice.getPriceValue()).getCent();
        orderItem.setUnitPrice(unitPrice);
        orderItem.setOriginPrice(unitPrice);
        orderItem.setQuantity(count);
        orderItem.setCostFee(count * unitPrice);
        orderItem.setDiscountFee(0L);
        return orderItem;
    }

}
