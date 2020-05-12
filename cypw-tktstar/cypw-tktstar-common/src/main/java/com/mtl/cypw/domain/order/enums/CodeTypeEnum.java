package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:06
 */
@Getter
@AllArgsConstructor
public enum CodeTypeEnum implements EntityEnum {

    CHECK_CODE(1, "检票码"),
    FETCH_TICKET_CODE(2, "取票码");

    private int code;
    private String name;

    public static CodeTypeEnum getObject(int code) {
        for (CodeTypeEnum codeTypeEnum : values()) {
            if (codeTypeEnum.getCode() == code) {
                return codeTypeEnum;
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
