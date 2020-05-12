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
public enum CheckChannelEnum implements EntityEnum {

    OFFLINE(1,"线下运营检票"),
    APPLET(2,"小程序检票"),
    PDA_DEVICE(4,"PDA设备检票"),
    TC_DEVICE(3,"闸机设备检票");

    private int code;
    private String name;

    public static CheckChannelEnum getObject(int code) {
        for (CheckChannelEnum channel : values()) {
            if (channel.getCode() == code) {
                return channel;
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
