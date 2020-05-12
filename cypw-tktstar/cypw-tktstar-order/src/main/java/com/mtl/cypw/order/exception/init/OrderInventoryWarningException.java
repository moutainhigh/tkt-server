package com.mtl.cypw.order.exception.init;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:06
 */
public class OrderInventoryWarningException extends OrderInitException{

    public OrderInventoryWarningException(String message) {
        super(message);
    }

    public OrderInventoryWarningException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_INIT_NOT_ENOUGH_INVENTORY.getCode();
    }
}
