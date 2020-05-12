package com.mtl.cypw.order.exception.ticket;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class ThirdPartyTicketException extends OrderTicketException {

    public ThirdPartyTicketException(String message) {
        super(message);
    }

    public ThirdPartyTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_TICKET_TP_FAILURE.getCode();
    }
}
