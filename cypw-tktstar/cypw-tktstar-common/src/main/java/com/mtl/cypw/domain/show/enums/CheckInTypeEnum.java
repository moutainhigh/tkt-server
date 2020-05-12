package com.mtl.cypw.domain.show.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 15:07
 */
@Getter
@AllArgsConstructor
public enum CheckInTypeEnum implements EntityEnum {

    BY_TIMES(1, "按次数核销（不限账号）"),
    BY_ENTRANCE(2, "按检票口核销（限账号&次数）");

    private int code;
    private String name;

    public static CheckInTypeEnum getObject(int code) {
        for (CheckInTypeEnum typeEnum : values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
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
