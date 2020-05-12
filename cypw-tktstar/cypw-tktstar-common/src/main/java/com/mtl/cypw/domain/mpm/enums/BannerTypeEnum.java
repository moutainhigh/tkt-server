package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-06 17:43
 */
@Getter
@AllArgsConstructor
public enum BannerTypeEnum implements EntityEnum {
    SHOWS(1, "演出类"),
    GOODS(2, "衍生品");

    private int code;
    private String name;

    public static BannerTypeEnum getObject(String name) {
        for (BannerTypeEnum typeEnum : values()) {
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
