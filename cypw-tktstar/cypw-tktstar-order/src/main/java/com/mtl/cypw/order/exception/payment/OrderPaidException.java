package com.mtl.cypw.order.exception.payment;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderPaidException extends OrderDealException {

    public OrderPaidException(String message) {
        super(message);
    }

    public OrderPaidException(String message, Throwable cause) {
        super(message, cause);
    }

}
