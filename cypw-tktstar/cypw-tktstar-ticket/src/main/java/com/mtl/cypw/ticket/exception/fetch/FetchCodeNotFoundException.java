package com.mtl.cypw.ticket.exception.fetch;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.ticket.exception.FetchBizException;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-17 14:17
 */
public class FetchCodeNotFoundException extends FetchBizException {

    public FetchCodeNotFoundException(String message) {
        super(message);
    }

    public FetchCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_TICKET_FETCH_CODE_FAILED.getCode();
    }
}
