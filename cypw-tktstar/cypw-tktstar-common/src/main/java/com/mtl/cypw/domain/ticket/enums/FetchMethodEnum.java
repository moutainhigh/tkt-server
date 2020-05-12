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
public enum FetchMethodEnum implements EntityEnum {

    ATM(1, "自助取票机取票"),
    PRINTER(2, "线下打印机打票"),
    APPLET(3, "小程序提货核销");

    private int code;
    private String name;

    public static FetchMethodEnum getObject(int code) {
        for (FetchMethodEnum method : values()) {
            if (method.getCode() == code) {
                return method;
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
