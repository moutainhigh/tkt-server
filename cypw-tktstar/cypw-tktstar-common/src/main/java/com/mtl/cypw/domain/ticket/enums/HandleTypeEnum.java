package com.mtl.cypw.domain.ticket.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 20:10
 */
@Getter
@AllArgsConstructor
public enum HandleTypeEnum implements EntityEnum {

    NORMAL(1,"正常取票/货"),
    REPEATED(2,"重新取票/货");

    private int code;
    private String name;

    public static HandleTypeEnum getObject(int code) {
        for (HandleTypeEnum type : values()) {
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
