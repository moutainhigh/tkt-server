package com.mtl.cypw.domain.stock.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 12:05
 */
@Getter
@AllArgsConstructor
public enum SeatSellTypeEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    SALE(1, "可售"),
    RESERVE(2, "预留");

    private int code;
    private String name;

    public static SeatSellTypeEnum getObject(int code) {
        for (SeatSellTypeEnum sellTypeEnum : values()) {
            if (sellTypeEnum.getCode() == code) {
                return sellTypeEnum;
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
