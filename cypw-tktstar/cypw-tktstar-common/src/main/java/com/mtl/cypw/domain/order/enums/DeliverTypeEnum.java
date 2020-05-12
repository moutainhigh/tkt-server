package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 12:01
 */
@Getter
@AllArgsConstructor
public enum DeliverTypeEnum implements EntityEnum {
    OFFLINE(1, "OFFLINE", "现场取票"),
    EXPRESS(2, "EXPRESS", "快递配送"),
    SPOT_PICKING(4, "SPOT_PICKING", "上门自取"),
    E_TICKET(5, "E_TICKET", "电子票");

    private int code;
    private String name;
    private String desc;

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

    public static DeliverTypeEnum getObject(int code) {
        for (DeliverTypeEnum deliverTypeEnum : values()) {
            if (deliverTypeEnum.getCode() == code) {
                return deliverTypeEnum;
            }
        }
        return null;
    }

    public static DeliverTypeEnum getObject(String name) {
        for (DeliverTypeEnum deliverTypeEnum : values()) {
            if (deliverTypeEnum.getName().equalsIgnoreCase(name)) {
                return deliverTypeEnum;
            }
        }
        return null;
    }
}
