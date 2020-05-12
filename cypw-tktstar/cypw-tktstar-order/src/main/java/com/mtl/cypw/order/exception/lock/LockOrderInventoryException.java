package com.mtl.cypw.order.exception.lock;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class LockOrderInventoryException extends OrderLockException {

    public LockOrderInventoryException(String message) {
        super(message);
    }

    public LockOrderInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_LOCK_INVENTORY_OP_FAILURE.getCode();
    }
}
