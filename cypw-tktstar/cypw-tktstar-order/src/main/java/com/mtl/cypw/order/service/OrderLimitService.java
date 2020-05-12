package com.mtl.cypw.order.service;

import com.mtl.cypw.order.model.Order;

/**
 * @author zhengyou.yuan
 * @date 2019-11-26 18:29
 */
public interface OrderLimitService {

    boolean consume(final Order order);

    boolean rollback(final Order order);
}
