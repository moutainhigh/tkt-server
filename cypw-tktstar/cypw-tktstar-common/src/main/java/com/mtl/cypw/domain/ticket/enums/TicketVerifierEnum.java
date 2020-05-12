package com.mtl.cypw.domain.ticket.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-21 11:31
 */
@Getter
@AllArgsConstructor
public enum TicketVerifierEnum implements EntityEnum {

    PASSED(1, "PASSED","验证通过"),
    SPENT(2, "SPENT","该票已入场");

    private int code;
    private String name;
    private String desc;

    public static TicketVerifierEnum getObject(int code) {
        for (TicketVerifierEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}

