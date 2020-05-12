package com.mtl.cypw.domain.show.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 15:27
 */
@Getter
@AllArgsConstructor
public enum SeatReservedOperationEnum implements EntityEnum {

    RESERVE(1, "设置预留"),
    RELEASED(2, "释放预留");


    private int code;
    private String name;

    public static SeatReservedOperationEnum getObject(int code) {
        for (SeatReservedOperationEnum operation : values()) {
            if (operation.getCode() == code) {
                return operation;
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
