package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 12:01
 */
@Getter
@AllArgsConstructor
public enum DeliverStatusEnum implements EntityEnum {

    UNDELIVERED(0, "默认未配送"),
    DELIVERING(1, "配送中"),
    DELIVERED(2, "已配送");

    private int code;
    private String name;

    public static DeliverStatusEnum getObject(int code) {
        for (DeliverStatusEnum statusEnum : values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
