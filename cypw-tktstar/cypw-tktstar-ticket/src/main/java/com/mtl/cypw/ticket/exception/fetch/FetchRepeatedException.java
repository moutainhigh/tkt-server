package com.mtl.cypw.ticket.exception.fetch;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.ticket.exception.FetchBizException;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-17 14:17
 */
public class FetchRepeatedException extends FetchBizException {

    public FetchRepeatedException(String message) {
        super(message);
    }

    public FetchRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_TICKET_FETCH_TICKET_REPEATED.getCode();
    }
}
