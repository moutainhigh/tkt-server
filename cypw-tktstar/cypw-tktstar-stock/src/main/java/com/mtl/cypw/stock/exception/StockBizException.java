package com.mtl.cypw.stock.exception;

import com.mtl.cypw.common.exception.GeneralException;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 13:35
 */
public class StockBizException extends GeneralException {

    public StockBizException(int code) {
        super(code);
    }

    public StockBizException(String message, int code) {
        super(message, code);
    }

    public StockBizException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

}
