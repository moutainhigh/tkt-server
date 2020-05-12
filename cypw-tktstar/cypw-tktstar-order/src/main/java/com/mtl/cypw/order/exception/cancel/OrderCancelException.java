package com.mtl.cypw.order.exception.cancel;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderCancelException extends OrderDealException {

    public OrderCancelException(String message) {
        super(message);
    }

    public OrderCancelException(String message, Throwable cause) {
        super(message, cause);
    }

}
