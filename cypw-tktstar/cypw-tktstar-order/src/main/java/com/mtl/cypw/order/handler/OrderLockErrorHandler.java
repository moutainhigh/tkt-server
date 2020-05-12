package com.mtl.cypw.order.handler;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.order.exception.OrderDealException;
import com.mtl.cypw.order.exception.lock.LockInventoryException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-31 11:52
 */
@Slf4j
@Component
public class OrderLockErrorHandler extends OrderDealErrorHandler<OrderDealException> {

    @Autowired
    private OrderService orderServiceImpl;

    @Override
    protected void doHandle(Order order, OrderDealException ore) {
        if (ore instanceof LockInventoryException) {
            this.unlockCoupon(order);
            this.releaseInventory(order);
        }
        if (!order.isSystemOrder()) {
            order.lockFailed();
        }
        orderServiceImpl.save(order);
    }

    private void unlockCoupon(Order order) {
        ThreadPoolUtil.execute(() -> {
            log.info("lock handler go to unlock coupon, orderNo [{}], datetime [{}]", order.getOrderNo(),
                    DateUtils.format(DateUtils.now(), DateUtils.millisecondFormat));
            orderServiceImpl.releaseCouponByOrderId(order);
        });
    }

    private void releaseInventory(Order order) {
        ThreadPoolUtil.execute(() -> {
            log.info("lock handler go to release inventory, orderNo [{}], datetime [{}]", order.getOrderNo(),
                    DateUtils.format(DateUtils.now(), DateUtils.millisecondFormat));
            orderServiceImpl.releaseInventoryByOrderId(order);
        });
    }
}
