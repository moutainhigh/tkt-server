package com.mtl.cypw.order.exception;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 14:17
 */
public abstract class OrderDealException extends RuntimeException {

    public OrderDealException(String message) {
        super(message);
    }

    public OrderDealException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 错误码
     * @see com.mtl.cypw.common.enums.ErrorCode
     * @return
     */
    public abstract int getCode();

}
