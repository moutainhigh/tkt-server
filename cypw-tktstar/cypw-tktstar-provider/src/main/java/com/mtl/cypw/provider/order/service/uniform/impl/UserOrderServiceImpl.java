package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.domain.order.param.UserCreateOrderParam;
import com.mtl.cypw.mall.service.ShoppingCartService;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderItem;
import com.mtl.cypw.provider.order.converter.UserOrderConverter;
import com.mtl.cypw.provider.order.service.uniform.AbstractUniformOrderService;
import com.mtl.cypw.provider.order.service.uniform.OrderAssertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 17:55
 */
@Slf4j
@Component(value = "userOrderService")
public class UserOrderServiceImpl extends AbstractUniformOrderService {

    @Autowired
    private UserOrderConverter converter;

    @Autowired
    private OrderAssertService orderAssertService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    protected void risk(Order order, UniformCreateOrderParam request) {

    }

    @Override
    protected void verify(Order order, UniformCreateOrderParam request) {
        Assert.notNull(order,"order is null");
        orderAssertService.assertNotDuplicated(order);
        orderAssertService.assertOrderCompleteness(order);
        orderAssertService.assertDeliveryCompleteness(order);
        orderAssertService.assertOrderAmountConsistent(order);
        orderAssertService.assertShowStatus(order);
        orderAssertService.assertSkuStatusAndLimit(order);
        orderAssertService.assertStock(order);
    }

    @Override
    protected boolean needConsumeLimit() {
        return false;
    }

    @Override
    protected boolean needTicketMapping() { return true; }

    @Override
    protected boolean needStartTicketAfterLock() {
        return false;
    }

    @Override
    protected boolean needCreateOrderForMtlCenter() {
        return false;
    }

    @Override
    public Order transform(UniformCreateOrderParam request) {
        Assert.isTrue(request instanceof UserCreateOrderParam,"UserCreateOrderParam invalid");
        UserCreateOrderParam param = (UserCreateOrderParam) request;
        return converter.convert(param);
    }

    @Override
    public void removeCart(Order order) {
        if (OrderTypeEnum.ONLY_GOODS.getCode() != order.getOrderType()) {
            return;
        }
        List<Integer> skuIds = order.getOrderItems().stream().map(OrderItem::getPriceId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(skuIds)) {
            ThreadPoolUtil.execute(() -> shoppingCartService.batchRemove(order.getMemberId(), skuIds));
        }
    }
}
