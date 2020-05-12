package com.mtl.cypw.order.exception.lock;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderLockException extends OrderDealException {

    public OrderLockException(String message) {
        super(message);
    }

    public OrderLockException(String message, Throwable cause) {
        super(message, cause);
    }

}
