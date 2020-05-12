package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 11:35
 */
@Getter
@AllArgsConstructor
public enum OrderTypeEnum implements EntityEnum {

    ONLY_TICKET(1, "票品"),
    ONLY_GOODS(2, "衍生品");

    private int code;
    private String name;

    public static OrderTypeEnum getObject(int code) {
        for (OrderTypeEnum orderTypeEnum : values()) {
            if (orderTypeEnum.getCode() == code) {
                return orderTypeEnum;
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
