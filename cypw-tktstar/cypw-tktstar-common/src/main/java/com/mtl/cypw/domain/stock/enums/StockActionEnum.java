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
public enum StockActionEnum implements EntityEnum {

    CONSUME(1, "库存消耗"),
    ROLLBACK(2,"库存回滚");

    private int code;
    private String name;

    public static StockActionEnum getObject(int code) {
        for (StockActionEnum recordStatusEnum : values()) {
            if (recordStatusEnum.getCode() == code) {
                return recordStatusEnum;
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
