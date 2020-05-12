package com.mtl.cypw.order.exception.sync;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderSyncException extends OrderDealException {

    public OrderSyncException(String message) {
        super(message);
    }

    public OrderSyncException(String message, Throwable cause) {
        super(message, cause);
    }

}
