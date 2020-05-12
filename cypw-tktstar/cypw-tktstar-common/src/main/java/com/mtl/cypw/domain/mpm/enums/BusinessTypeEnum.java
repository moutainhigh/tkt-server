package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 16:47
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum implements EntityEnum {
    PIC_COMMON(0, "通用图片"),
    PIC_GOODS_SPEC(1, "衍生品规格图片");

    private int code;
    private String name;

    public static BusinessTypeEnum getObject(String name) {
        for (BusinessTypeEnum typeEnum : values()) {
            if (typeEnum.getName().equalsIgnoreCase(name)) {
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
