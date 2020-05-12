package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 13:44
 */
@Getter
@AllArgsConstructor
public enum CodeSourceEnum implements EntityEnum {

    SELF_CODE(0, "自有检票码"),
    THIRD_PARTY_CODE(1, "第三方检票码");

    private int code;
    private String name;

    public static CodeSourceEnum getObject(int code) {
        for (CodeSourceEnum sourceEnum : values()) {
            if (sourceEnum.getCode() == code) {
                return sourceEnum;
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