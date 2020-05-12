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
public enum CheckMethodEnum implements EntityEnum {

    QR_CODE(1, "二维码检票"),
    MANUALLY(2, "手工输入检票"),
    RFID(3, "RFID检票"),
    ID_CARD(4, "身份证检票");

    private int code;
    private String name;

    public static CheckMethodEnum getObject(int code) {
        for (CheckMethodEnum method : values()) {
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
