package com.mtl.cypw.ticket.exception;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-17 14:17
 */
public abstract class TicketBizException extends RuntimeException {

    public TicketBizException(String message) {
        super(message);
    }

    public TicketBizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 错误码
     * @see com.mtl.cypw.common.enums.ErrorCode
     * @return
     */
    public abstract int getCode();

}
