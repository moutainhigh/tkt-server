package com.mtl.cypw.order.exception.consume;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderConsumeException extends OrderDealException {

    public OrderConsumeException(String message) {
        super(message);
    }

    public OrderConsumeException(String message, Throwable cause) {
        super(message, cause);
    }

}
