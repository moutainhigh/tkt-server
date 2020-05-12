package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 17:43
 */
@Getter
@AllArgsConstructor
public enum LinkTypeEnum implements EntityEnum {
    INTERNAL(1, "INTERNAL", "内链"),
    EXTERNAL(2, "EXTERNAL", "外链");

    private int code;
    private String name;
    private String desc;

    public static LinkTypeEnum getObject(String name) {
        for (LinkTypeEnum typeEnum : values()) {
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
