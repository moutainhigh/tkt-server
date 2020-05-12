package com.mtl.cypw.domain.stock.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 15:27
 */
@Getter
@AllArgsConstructor
public enum ReserveSeatStatusEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    RESERVED(1, "预留中"),
    TICKETED(2, "已出票"),
    RELEASED(3, "已释放");


    private int code;
    private String name;

    public static ReserveSeatStatusEnum getObject(int code) {
        for (ReserveSeatStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return UNKNOWN;
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
