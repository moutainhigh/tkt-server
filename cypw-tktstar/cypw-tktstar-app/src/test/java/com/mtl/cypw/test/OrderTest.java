package com.mtl.cypw.test;

import com.google.common.collect.Lists;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.juqitech.response.paging.SortingCondition;
import com.juqitech.service.utils.CommonUtil;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.OrderCancelEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.param.OrderCancelParam;
import com.mtl.cypw.domain.order.param.OrderPaidParam;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.domain.order.param.OrderTicketParam;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.repository.OrderRepository;
import com.mtl.cypw.provider.order.service.biz.OrderOpEntrance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-19 19:18
 */
@Slf4j
public class OrderTest extends BaseTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderOpEntrance orderOpEntrance;

    @Test
    public void cancelTest() {
        OrderQueryParam param = OrderQueryParam.builder()
                .orderStatus(OrderStatusEnum.LOCKED.getCode())
                .build();
        int pageNo = 1;
        Pagination pagination = new Pagination(pageNo, 100);
        pagination.setSortingConditions(Lists.newArrayList(new SortingCondition("id", SortingCondition.SortingMethod.ASC, false)));
        List<Order> orders = orderRepository.pageQuery(param, pagination);
        while (CollectionUtils.isNotEmpty(orders)) {
            for (Order order : orders) {
                OrderCancelParam cancelParam = OrderCancelParam.builder()
                        .orderId(order.getId())
                        .cancelEnum(OrderCancelEnum.PAY_TIMEOUT)
                        .build();
                orderOpEntrance.cancelOrder(cancelParam);
            }
            pagination.setPageNo(pageNo++);
            orders = orderRepository.pageQuery(param, pagination);
        }
    }

    @Test
    public void paidTest() {
        OrderPaidParam param = new OrderPaidParam();
        param.setSerialNo(CommonUtil.generateOID());
        param.setOrderPrice(new Money(0.01));
        param.setOrderId(272);
        param.setPayType(PayTypeEnum.WECHAT_PAY);
        TSingleResult<Boolean> result = orderOpEntrance.paidOrder(param);
        log.info("paid result -> " + JsonUtils.toJson(result));
    }


    @Test
    public void ticketTest() {
        OrderTicketParam param = OrderTicketParam.builder().orderId(1140).build();

        TSingleResult<Boolean> result = orderOpEntrance.ticketOrder(param);
        log.info("paid result -> " + JsonUtils.toJson(result));
    }
}
