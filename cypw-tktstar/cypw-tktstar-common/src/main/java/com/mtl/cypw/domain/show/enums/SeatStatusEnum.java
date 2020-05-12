package com.mtl.cypw.domain.show.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-03 15:07
 */
@Getter
@AllArgsConstructor
public enum SeatStatusEnum implements EntityEnum {

    UNKNOWN(0, "空白区域"),
    CAN_BE_SOLD(1, "可售座位"),
    RESERVED(2, "预留座位"),
    HAS_BEEN_SOLD(3, "已售座位"),
    UNSALEABLE(4, "不可售座位");

    private int code;
    private String name;

    public static SeatStatusEnum getObject(int code) {
        for (SeatStatusEnum status : values()) {
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
