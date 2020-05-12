package com.mtl.cypw.order.exception.lock;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class LockCouponCodeException extends OrderLockException {

    public LockCouponCodeException(String message) {
        super(message);
    }

    public LockCouponCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_LOCK_COUPON_CODE_FAILURE.getCode();
    }
}
