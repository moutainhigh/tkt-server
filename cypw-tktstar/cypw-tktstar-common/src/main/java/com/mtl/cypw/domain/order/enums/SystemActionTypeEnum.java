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
public enum SystemActionTypeEnum implements EntityEnum {

    GENERAL_ORDER(1, "普通下单"),
    RESERVE_ORDER(2, "预留出票"),
    POOL_ORDER(3, "票池出票");

    private int code;
    private String name;

    public static SystemActionTypeEnum getObject(int code) {
        for (SystemActionTypeEnum actionTypeEnum : values()) {
            if (actionTypeEnum.getCode() == code) {
                return actionTypeEnum;
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
