package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 12:11
 */
@Getter
@AllArgsConstructor
public enum ConsumeTypeEnum implements EntityEnum {

    TICKET(1, "普通票品"),
    SEAT_TICKET(2, "选座票品"),
    GOODS(3, "衍生品");

    private int code;
    private String name;

    public static ConsumeTypeEnum getObject(int code) {
        for (ConsumeTypeEnum consumeTypeEnum : values()) {
            if (consumeTypeEnum.getCode() == code) {
                return consumeTypeEnum;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
