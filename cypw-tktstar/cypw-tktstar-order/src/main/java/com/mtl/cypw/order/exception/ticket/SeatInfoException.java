package com.mtl.cypw.order.exception.ticket;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class SeatInfoException extends OrderTicketException {

    public SeatInfoException(String message) {
        super(message);
    }

    public SeatInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_TICKET_SEAT_INFO_ERROR.getCode();
    }
}
