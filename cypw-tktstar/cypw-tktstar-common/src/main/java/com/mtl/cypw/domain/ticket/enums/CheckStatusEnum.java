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
public enum CheckStatusEnum implements EntityEnum {

    NOT_CHECK(0, "未检票"),
    CHECKING(1, "已检票"),
    COMPLETED(2, "已完成");

    private int code;
    private String name;

    public static CheckStatusEnum getObject(int code) {
        for (CheckStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
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
