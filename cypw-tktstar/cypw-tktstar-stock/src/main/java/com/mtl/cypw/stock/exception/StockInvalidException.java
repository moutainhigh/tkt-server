package com.mtl.cypw.stock.exception;

import com.mtl.cypw.common.exception.GeneralException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public class StockInvalidException extends GeneralException {

    public StockInvalidException(int code) {
        super(code);
    }

    public StockInvalidException(String message, int code) {
        super(message, code);
    }

    public StockInvalidException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

}
