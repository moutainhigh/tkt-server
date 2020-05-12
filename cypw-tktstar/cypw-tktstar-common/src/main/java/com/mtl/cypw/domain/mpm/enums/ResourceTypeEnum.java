package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 17:28
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum implements EntityEnum {
    SHOW(1, "SHOW", "演出"),
    MALL(2, "MALL", "衍生品");

    private int code;
    private String name;
    private String desc;

    public static ResourceTypeEnum getObject(String name) {
        for (ResourceTypeEnum typeEnum : values()) {
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