package com.mtl.cypw.order.service;

import com.mtl.cypw.domain.order.param.BindExpressNoParam;
import com.mtl.cypw.domain.order.param.OrderCancelParam;
import com.mtl.cypw.domain.order.param.OrderRefundParam;
import com.mtl.cypw.order.exception.OrderBizException;
import com.mtl.cypw.order.exception.cancel.OrderCancelException;
import com.mtl.cypw.order.exception.lock.LockInventoryException;
import com.mtl.cypw.order.exception.lock.OrderLockException;
import com.mtl.cypw.order.exception.payment.OrderPaidException;
import com.mtl.cypw.order.exception.refund.OrderRefundException;
import com.mtl.cypw.order.exception.sync.OrderSyncException;
import com.mtl.cypw.order.exception.ticket.OrderTicketException;
import com.mtl.cypw.order.model.Order;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:20
 */
public interface OrderService {

    Order save(Order order) throws OrderBizException;

    Order lock(Order order) throws OrderLockException;

    Order paid(Order order) throws OrderPaidException;

    Order ticket(Order order) throws OrderTicketException;

    Order refund(OrderRefundParam request) throws OrderRefundException;

    Order cancel(OrderCancelParam request) throws OrderCancelException;

    Order syncOrder(Order order) throws OrderSyncException;

    Order lockInventory(Order order) throws LockInventoryException;

    Order orderInventoryAndStore(Order order) throws OrderLockException;

    Order releaseInventoryByOrderId(Order order);

    Order releaseCouponByOrderId(Order order);

    Order tryLockStock(Order order) throws LockInventoryException;

    void confirmReceipt(int orderId);

    Order bindExpressNo(BindExpressNoParam request);
}
