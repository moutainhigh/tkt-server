package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 13:44
 */
@Getter
@AllArgsConstructor
public enum OrderCancelEnum implements EntityEnum {

    PAY_TIMEOUT(1, "支付超时"),
    USER_CANCEL(2, "用户主动取消"),
    MERCHANT_CANCEL(3, "商家取消");

    private int code;
    private String name;

    public static OrderCancelEnum getObject(int code) {
        for (OrderCancelEnum cancelEnum : values()) {
            if (cancelEnum.getCode() == code) {
                return cancelEnum;
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