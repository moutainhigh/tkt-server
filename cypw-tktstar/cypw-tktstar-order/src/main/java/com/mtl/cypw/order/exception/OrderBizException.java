package com.mtl.cypw.order.exception;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public class OrderBizException extends OrderDealException {

    public OrderBizException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER.getCode();
    }
}
