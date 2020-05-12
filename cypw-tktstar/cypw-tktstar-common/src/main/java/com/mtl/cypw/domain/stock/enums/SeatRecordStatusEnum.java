package com.mtl.cypw.domain.stock.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-03 15:07
 */
@Getter
@AllArgsConstructor
public enum SeatRecordStatusEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    ALREADY_ORDER(1, "已下单"),
    ALREADY_RELEASED(2, "已释放");

    private int code;
    private String name;

    public static SeatRecordStatusEnum getObject(int code) {
        for (SeatRecordStatusEnum status : values()) {
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
