package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.order.exception.init.OrderInitException;
import com.mtl.cypw.order.model.Order;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 11:15
 */
public interface OrderAssertService {

    /**
     * 用户订单数量校验
     * @param order
     * @throws OrderInitException
     */
    void assertNotDuplicated(Order order) throws OrderInitException;

    /**
     * 订单信息完整度校验
     * @param order
     * @throws OrderInitException
     */
    void assertOrderCompleteness(Order order) throws OrderInitException;

    /**
     * 配送方式完整度校验
     * @param order
     * @throws OrderInitException
     */
    void assertDeliveryCompleteness(Order order) throws OrderInitException;

    /**
     * 订单金额一致性校验
     * @param order
     * @throws OrderInitException
     */
    void assertOrderAmountConsistent(Order order) throws OrderInitException;

    /**
     * 演出可售卖状态校验
     * @param order
     * @throws OrderInitException
     */
    void assertShowStatus(Order order) throws OrderInitException;

    /**
     * 商品可售卖状态及售卖数量校验
     * @param order
     * @throws OrderInitException
     */
    void assertSkuStatusAndLimit(Order order) throws OrderInitException;

    /**
     * 订单库存校验
     * @param order
     * @throws OrderInitException
     */
    void assertStock(Order order) throws OrderInitException;
}
