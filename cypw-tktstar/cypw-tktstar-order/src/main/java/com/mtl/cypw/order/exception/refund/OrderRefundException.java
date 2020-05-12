package com.mtl.cypw.order.exception.refund;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderRefundException extends OrderDealException {

    public OrderRefundException(String message) {
        super(message);
    }

    public OrderRefundException(String message, Throwable cause) {
        super(message, cause);
    }

}
