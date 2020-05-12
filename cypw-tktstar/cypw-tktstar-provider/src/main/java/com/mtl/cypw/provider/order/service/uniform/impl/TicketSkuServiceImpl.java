package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.param.UniformSkuParam;
import com.mtl.cypw.order.exception.init.TicketStatusException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderItem;
import com.mtl.cypw.provider.order.service.uniform.SkuService;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-03 12:01
 */
@Slf4j
@Component("ticketSkuService")
public class TicketSkuServiceImpl implements SkuService {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventPriceService eventPriceService;

    @Override
    public OrderItem buildOrderItem(Order order, UniformSkuParam param) {
        log.info("build ticket order items, param:{}", JsonUtils.toJson(param));
        Assert.notNull(order, "order is null");
        Assert.notNull(param, "sku is null");
        Assert.isTrue(SkuTypeEnum.TICKET.getCode() == param.getType().getCode(), "sku error");
        EventPrice eventPrice = eventPriceService.getEventPriceById(param.getSkuId());
        if (eventPrice == null) {
            log.error("不存在的票品信息, param:{}", JsonUtils.toJson(param));
            throw new TicketStatusException("不存在的票品信息");
        }
        Event event = eventService.findOneById(eventPrice.getEventId());
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(event.getEventId());
        orderItem.setProductTitle(StringUtils.isNotEmpty(event.getEventTitle()) ? event.getEventTitle() : eventPrice.getPriceTitle());
        orderItem.setEnterpriseId(event.getEnterpriseId());
        orderItem.setIsPackage(0);
        orderItem.setPackageNumber(1);
        orderItem.setSkuType(SkuTypeEnum.TICKET.getCode());
        orderItem.setPriceId(param.getSkuId());
        orderItem.setPriceDesc(eventPrice.getPriceTitle());
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
