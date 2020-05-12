package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 20:07
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements EntityEnum {
    INIT(0, "INIT", "已提交"),
    LOCK_FAILED(1, "LOCK_FAILED", "锁座失败"),
    CANCELED(2, "CANCELED", "已取消"),
    LOCKED(3, "LOCKED", "已锁座"),
    EXPIRED(4, "EXPIRED", "支付超时"),
    PAY_FAILED(5, "PAY_FAILED" ,"支付失败"),
    PAID(6, "PAID", "支付成功"),
    TICKETING(7, "TICKETING", "出票中"),
    TICKET_FAILED(8, "TICKET_FAILED", "出票失败"),
    TICKETED(9,"TICKETED", "已出票"),
    DELIVERED(10, "DELIVERED", "已交付"),
    REFUNDING(11, "REFUNDING", "退票中"),
    REFUNDED(12, "REFUNDED", "已退票"),
    DELIVERING(13, "DELIVERING", "配送中"),
    SUCCEED(18, "SUCCEED", "已完成"),
    CLOSED(99, "CLOSED", "已终止");

    private int code;
    private String name;
    private String desc;

    public static OrderStatusEnum getObject(int code) {
        for (OrderStatusEnum statusEnum : values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
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

    public String getDesc() { return desc; }
}
