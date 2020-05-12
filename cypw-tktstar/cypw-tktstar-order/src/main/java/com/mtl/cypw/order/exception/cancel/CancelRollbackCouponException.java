package com.mtl.cypw.order.exception.cancel;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class CancelRollbackCouponException extends OrderCancelException {

    public CancelRollbackCouponException(String message) {
        super(message);
    }

    public CancelRollbackCouponException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_CANCEL_ROLLBACK_INVENTORY_ERROR.getCode();
    }
}
