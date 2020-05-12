package com.mtl.cypw.order.exception.cancel;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class AlreadyCancelledException extends OrderCancelException {

    public AlreadyCancelledException(String message) {
        super(message);
    }

    public AlreadyCancelledException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_CANCEL_ALREADY_CANCELLED.getCode();
    }
}
