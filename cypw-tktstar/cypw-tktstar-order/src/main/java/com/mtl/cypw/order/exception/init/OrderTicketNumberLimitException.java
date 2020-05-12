package com.mtl.cypw.order.exception.init;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:06
 */
public class OrderTicketNumberLimitException extends OrderInitException{

    public OrderTicketNumberLimitException(String message) {
        super(message);
    }

    public OrderTicketNumberLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_INIT_TICKET_NUMS_OVER.getCode();
    }
}
