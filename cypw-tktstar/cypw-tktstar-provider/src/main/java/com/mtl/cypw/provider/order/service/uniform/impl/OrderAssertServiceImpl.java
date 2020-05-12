package com.mtl.cypw.provider.order.service.uniform.impl;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.exception.init.OrderDuplicatedException;
import com.mtl.cypw.order.exception.init.OrderInitException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.repository.OrderRepository;
import com.mtl.cypw.provider.order.service.uniform.OrderAssertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 12:05
 */
@Slf4j
@Service
public class OrderAssertServiceImpl implements OrderAssertService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void assertNotDuplicated(Order order) throws OrderInitException {
        if (order == null || OrderTypeEnum.ONLY_GOODS.getCode() == order.getOrderType()) {
            log.debug("衍生品不限制订单数量");
            return;
        }
        OrderQueryParam param = OrderQueryParam.builder()
                .orderStatus(OrderStatusEnum.LOCKED.getCode())
                .build();
        param.setMemberId(order.getMemberId());
        param.setProductId(order.getProductId());
        param.setEnterpriseId(order.getEnterpriseId());
        List<Order> orders = orderRepository.pageQuery(param, new Pagination());
        if (CollectionUtils.isNotEmpty(orders)) {
            // C端：去支付 or 取消兼容
            throw new OrderDuplicatedException(String.valueOf(orders.get(0).getId()));
        }
    }

    @Override
    public void assertOrderCompleteness(Order order) throws OrderInitException {

    }

    @Override
    public void assertDeliveryCompleteness(Order order) throws OrderInitException {


    }

    @Override
    public void assertOrderAmountConsistent(Order order) throws OrderInitException {

    }

    @Override
    public void assertShowStatus(Order order) throws OrderInitException {

    }

    @Override
    public void assertSkuStatusAndLimit(Order order) throws OrderInitException {

    }

    @Override
    public void assertStock(Order order) throws OrderInitException {

    }
}
