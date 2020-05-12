package com.mtl.cypw.ticket.exception.fetch;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.ticket.exception.FetchBizException;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-17 14:17
 */
public class FetchShowClosedException extends FetchBizException {

    public FetchShowClosedException(String message) {
        super(message);
    }

    public FetchShowClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_TICKET_BASE_SHOW_CLOSED.getCode();
    }
}
