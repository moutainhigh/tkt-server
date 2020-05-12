package com.mtl.cypw.domain.coupon.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-11 16:47
 */
@Getter
@AllArgsConstructor
public enum PromotionBusinessTypeEnum implements EntityEnum {
    BIZ_TICKET(1, "票品类"),
    BIZ_GOODS(2, "衍生品");

    private int code;
    private String name;

    public static PromotionBusinessTypeEnum getObject(int code) {
        for (PromotionBusinessTypeEnum typeEnum : values()) {
            if (typeEnum.code == code) {
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
