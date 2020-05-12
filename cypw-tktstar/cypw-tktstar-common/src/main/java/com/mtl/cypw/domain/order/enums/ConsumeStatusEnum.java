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
public enum ConsumeStatusEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    ALREADY_LOCKED(1, "已锁库存"),
    ALREADY_ORDER(2, "已下单"),
    RELEASED(3, "库存已释放"),
    WAIT_RELEASE(4,"库存待释放");

    private int code;
    private String name;

    public static ConsumeStatusEnum getObject(int code) {
        for (ConsumeStatusEnum consumeStatusEnum : values()) {
            if (consumeStatusEnum.getCode() == code) {
                return consumeStatusEnum;
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
