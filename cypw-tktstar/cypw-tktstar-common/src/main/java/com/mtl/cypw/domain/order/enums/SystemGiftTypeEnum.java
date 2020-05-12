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
public enum SystemGiftTypeEnum implements EntityEnum {

    GENERAL_TICKET(1, "普通票"),
    PRESENT_TICKET(2, "赠票"),
    WORK_TICKET(3, "工作票");

    private int code;
    private String name;

    public static SystemGiftTypeEnum getObject(int code) {
        for (SystemGiftTypeEnum actionTypeEnum : values()) {
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
