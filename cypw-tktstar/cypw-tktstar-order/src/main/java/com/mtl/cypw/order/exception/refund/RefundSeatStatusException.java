package com.mtl.cypw.order.exception.refund;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class RefundSeatStatusException extends OrderRefundException {

    public RefundSeatStatusException(String message) {
        super(message);
    }

    public RefundSeatStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_REFUND_ROLLBACK_SEAT_STATUS_ERROR.getCode();
    }
}
