package com.mtl.cypw.order.service.impl;

import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 18:31
 */
@Slf4j
@Service
public class OrderLimitServiceImpl implements OrderLimitService {

    @Override
    public boolean consume(Order order) {
        Assert.notNull(order, "order is empty.");

        if (order.isTicket()) {
            // 项目限购

            // 场次限购
        } else if (order.isGoods()) {
            // 衍生品限购
        }
        return true;
    }

    @Override
    public boolean rollback(Order order) {
        Assert.notNull(order, "order is empty.");
        // 限购库存回滚

        return true;
    }
}
