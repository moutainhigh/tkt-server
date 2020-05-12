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
public enum FetchStatusEnum implements EntityEnum {

    NOT_FETCHED(0, "未取票"),
    FETCHING(1, "取票锁定中"),
    PART_FETCHED(2, "部分取票"),
    FETCHED(3, "已取票");

    private int code;
    private String name;

    public static FetchStatusEnum getObject(int code) {
        for (FetchStatusEnum method : values()) {
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
