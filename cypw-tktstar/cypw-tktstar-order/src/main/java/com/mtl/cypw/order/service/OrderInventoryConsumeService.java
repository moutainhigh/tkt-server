package com.mtl.cypw.order.service;

import com.mtl.cypw.order.exception.lock.LockInventoryException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderInventoryConsumeRecord;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 10:26
 */
public interface OrderInventoryConsumeService {

    OrderInventoryConsumeRecord update(OrderInventoryConsumeRecord consumeRecord);

    OrderInventoryConsumeRecord insert(OrderInventoryConsumeRecord consumeRecord);

    OrderInventoryConsumeRecord findOne(Integer recordId);

    OrderInventoryConsumeRecord findOneByOrderId(Integer orderId);

    /**
     * 订单消耗库存
     * @param order
     * @return
     */
    Order orderInventory(Order order);
}
