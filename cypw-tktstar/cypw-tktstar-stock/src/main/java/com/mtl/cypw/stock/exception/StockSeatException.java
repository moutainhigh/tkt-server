package com.mtl.cypw.stock.exception;

import com.mtl.cypw.common.exception.GeneralException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public class StockSeatException extends GeneralException {

    public StockSeatException(int code) {
        super(code);
    }

    public StockSeatException(String message, int code) {
        super(message, code);
    }

    public StockSeatException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

}
