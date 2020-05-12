package com.mtl.cypw.order.exception.init;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:06
 */
public class OrderCompletenessException extends OrderInitException{

    public OrderCompletenessException(String message) {
        super(message);
    }

    public OrderCompletenessException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_INIT_COMPLETENESS.getCode();
    }
}
