package com.mtl.cypw.domain.ticket.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 20:08
 */
@Getter
@AllArgsConstructor
public enum DeviceTypeEnum implements EntityEnum {

    TELEPHONE(1,"移动手机"),
    PDA(2,"PDA设备"),
    SELF_GATE(3,"自研闸机");

    private int code;
    private String name;

    public static DeviceTypeEnum getObject(int code) {
        for (DeviceTypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
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
