package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:06
 */
@Getter
@AllArgsConstructor
public enum ChannelEnum implements EntityEnum {

    STORE(1, "线上店铺销售"),
    BACKEND(2, "线下门店销售"),
    CHANNEL(3, "渠道分销");

    private int code;
    private String name;

    public static ChannelEnum getObject(int code) {
        for (ChannelEnum channel : values()) {
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
    }}
