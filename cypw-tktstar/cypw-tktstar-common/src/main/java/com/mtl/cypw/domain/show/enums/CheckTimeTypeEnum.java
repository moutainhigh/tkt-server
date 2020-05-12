package com.mtl.cypw.domain.show.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 15:07
 */
@Getter
@AllArgsConstructor
public enum CheckTimeTypeEnum implements EntityEnum {

    FIXED_TIME(0, "按场次时间核销"),
    ANY_TIME(1, "任意时间核销");

    private int code;
    private String name;

    public static CheckTimeTypeEnum getObject(int code) {
        for (CheckTimeTypeEnum typeEnum : values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
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
