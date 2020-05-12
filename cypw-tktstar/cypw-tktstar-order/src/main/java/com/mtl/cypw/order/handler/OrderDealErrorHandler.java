package com.mtl.cypw.order.handler;

import com.mtl.cypw.order.exception.OrderDealException;
import com.mtl.cypw.order.model.Order;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Johnathon.Yuan
 * @date 2019-12-31 11:47
 */
@Slf4j
public abstract class OrderDealErrorHandler <T extends OrderDealException> {

    public void handle(Order order, T ore) {
        try {
            doHandle(order, ore);
        } catch (Exception e) {
            log.error("Failed to handle exception: [{}].", ore, e);
        }
    }

    /**
     * 执行器
     * @param order
     * @param ore
     */
    protected abstract void doHandle(Order order, T ore);
}
