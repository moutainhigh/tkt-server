package com.mtl.cypw.job.order;

import com.google.common.collect.Lists;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.juqitech.response.paging.SortingCondition;
import com.mtl.common.jobexecutor.service.jobhandler.AbstractJobHandler;
import com.mtl.cypw.domain.order.enums.OrderCancelEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.param.OrderCancelParam;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.repository.OrderRepository;
import com.mtl.cypw.provider.order.service.biz.OrderOpEntrance;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-18 22:53
 */
@Slf4j
@Component
@JobHandler(value="AutoCancelTimeoutOrderJob")
public class AutoCancelTimeoutOrderJob extends AbstractJobHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderOpEntrance orderOpEntrance;

    @Override
    protected void doExecute(String s) {
        OrderQueryParam param = OrderQueryParam.builder()
                .orderStatus(OrderStatusEnum.LOCKED.getCode())
                .build();
        int pageNo = 1;
        Pagination pagination = new Pagination(pageNo, 100);
        pagination.setSortingConditions(Lists.newArrayList(new SortingCondition("id", SortingCondition.SortingMethod.ASC, false)));
        List<Order> orders = orderRepository.pageQuery(param, pagination);
        while (CollectionUtils.isNotEmpty(orders)) {
            for (Order order : orders) {
                if (!order.isPaymentTimeout()) {
                    continue;
                }
                OrderCancelParam cancelParam = OrderCancelParam.builder()
                        .orderId(order.getId())
                        .cancelEnum(OrderCancelEnum.PAY_TIMEOUT)
                        .build();
                TSingleResult<Boolean> result = orderOpEntrance.cancelOrder(cancelParam);
                if (!result.success()) {
                    log.error("Cancel timeout order exception, orderNo:{}, code:{}, message:{}",
                            order.getOrderNo(), result.getStatusCode(), result.getComments());
                }
            }
            pagination.setPageNo(pageNo++);
            orders = orderRepository.pageQuery(param, pagination);
        }
    }
}

