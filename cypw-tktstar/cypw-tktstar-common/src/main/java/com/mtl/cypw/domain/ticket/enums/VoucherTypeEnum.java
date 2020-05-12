package com.mtl.cypw.domain.ticket.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 20:08
 */
@Getter
@AllArgsConstructor
public enum VoucherTypeEnum implements EntityEnum {

    TICKET(1,"票品"),
    GOODS(2,"衍生品");

    private int code;
    private String name;

    public static VoucherTypeEnum getObject(int code) {
        for (VoucherTypeEnum type : values()) {
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
