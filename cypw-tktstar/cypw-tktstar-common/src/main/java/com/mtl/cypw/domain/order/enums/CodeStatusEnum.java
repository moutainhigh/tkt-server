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
public enum CodeStatusEnum implements EntityEnum {

    NOT_USED(0, "未被使用"),
    USED(1, "已被使用");

    private int code;
    private String name;

    public static CodeStatusEnum getObject(int code) {
        for (CodeStatusEnum codeStatusEnum : values()) {
            if (codeStatusEnum.getCode() == code) {
                return codeStatusEnum;
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