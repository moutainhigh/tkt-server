package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 12:11
 */
@Getter
@AllArgsConstructor
public enum IdentityTypeEnum implements EntityEnum {

    UNKNOWN(1, "UNKNOWN", "未知"),
    IDENTITY_CARD(2, "IDENTITY_CARD", "身份证"),
    PASS_PORT(3, "PASS_PORT", "护照");

    private int code;
    private String name;
    private String desc;

    public static IdentityTypeEnum getObject(int code) {
        for (IdentityTypeEnum identityTypeEnum : values()) {
            if (identityTypeEnum.getCode() == code) {
                return identityTypeEnum;
            }
        }
        return null;
    }

    public static IdentityTypeEnum getObject(String name) {
        for (IdentityTypeEnum identityTypeEnum : values()) {
            if (identityTypeEnum.getName().equalsIgnoreCase(name)) {
                return identityTypeEnum;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }
}
