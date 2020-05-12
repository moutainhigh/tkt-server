package com.mtl.cypw.order.exception.refund;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class RefundOrderInfoException extends OrderRefundException {

    public RefundOrderInfoException(String message) {
        super(message);
    }

    public RefundOrderInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_REFUND_INCORRECT_ORDER_INFO.getCode();
    }
}
