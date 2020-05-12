package com.mtl.cypw.order.exception.ticket;

import com.mtl.cypw.order.exception.OrderDealException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public abstract class OrderTicketException extends OrderDealException {

    public OrderTicketException(String message) {
        super(message);
    }

    public OrderTicketException(String message, Throwable cause) {
        super(message, cause);
    }

}
