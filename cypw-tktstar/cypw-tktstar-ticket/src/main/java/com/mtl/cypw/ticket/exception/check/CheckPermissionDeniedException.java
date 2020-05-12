package com.mtl.cypw.ticket.exception.check;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.ticket.exception.TicketBizException;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-17 14:17
 */
public class CheckPermissionDeniedException extends TicketBizException {

    public CheckPermissionDeniedException(String message) {
        super(message);
    }

    public CheckPermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return ErrorCode.ERROR_TICKET_CHECK_PERMISSION_DENIED.getCode();
    }
}
