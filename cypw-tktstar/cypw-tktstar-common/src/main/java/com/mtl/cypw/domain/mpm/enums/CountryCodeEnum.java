package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-23 12:03
 */
@Getter
@AllArgsConstructor
public enum CountryCodeEnum implements EntityEnum {

    CN(86, "CN");

    private int code;

    private String name;

    public static CountryCodeEnum getObject(String name) {
        for (CountryCodeEnum platform : values()) {
            if (platform.getName().equalsIgnoreCase(name)) {
                return platform;
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
