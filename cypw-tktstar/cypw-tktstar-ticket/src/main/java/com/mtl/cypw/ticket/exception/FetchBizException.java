package com.mtl.cypw.ticket.exception;

/**
 * @author Johnathon.Yuan
 * @date 2019-03-19 12:06
 */
public abstract class FetchBizException extends RuntimeException {

    public FetchBizException(String message) {
        super(message);
    }

    public FetchBizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 错误码
     * @see com.mtl.cypw.common.enums.ErrorCode
     * @return
     */
    public abstract int getCode();

}
