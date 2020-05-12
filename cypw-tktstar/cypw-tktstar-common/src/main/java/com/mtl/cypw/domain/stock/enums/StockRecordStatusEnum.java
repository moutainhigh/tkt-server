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
public enum StockRecordStatusEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    TRY_LOCK_SUCCESS(1, "库存预扣"),
    ROLLBACK(2,"库存回滚");

    private int code;
    private String name;

    public static StockRecordStatusEnum getObject(int code) {
        for (StockRecordStatusEnum recordStatusEnum : values()) {
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
