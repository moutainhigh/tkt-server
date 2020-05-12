package com.mtl.cypw.order.exception.init;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderInitException extends OrderDealException {

    public OrderInitException(String message) {
        super(message);
    }

    public OrderInitException(String message, Throwable cause) {
        super(message, cause);
    }

}
