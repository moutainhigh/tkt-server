package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-10 17:20
 */
@Getter
@AllArgsConstructor
public enum SvgMapTypeEnum implements EntityEnum {
    SVH_AREA_MAP(0, "C端显示区域图层，区域选座"),
    SVG_SEAT_MAP(1, "C端显示全部座位，直接选座");

    private int code;
    private String name;

    public static SvgMapTypeEnum getObject(String name) {
        for (SvgMapTypeEnum typeEnum : values()) {
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
