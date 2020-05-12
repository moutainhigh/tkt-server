package com.mtl.cypw.order.exception.init;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:06
 */
public class CouponMultiException extends OrderInitException{

    public CouponMultiException(String message) {
        super(message);
    }

    public CouponMultiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_INIT_MULTI_COUPON_GROUP_DISCOUNT.getCode();
    }
}
