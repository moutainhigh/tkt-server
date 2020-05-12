package com.mtl.cypw.order.exception.lock;

import com.mtl.cypw.common.enums.ErrorCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-27 16:17
 */
public class LockInventoryException extends OrderLockException {

    public LockInventoryException(String message) {
        super(message);
    }

    public LockInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_ORDER_LOCK_INVENTORY_FAILURE.getCode();
    }
}
