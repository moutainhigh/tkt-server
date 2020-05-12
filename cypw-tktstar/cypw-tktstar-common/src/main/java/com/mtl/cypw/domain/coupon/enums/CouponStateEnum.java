package com.mtl.cypw.domain.coupon.enums;

/**
 * @author tang.
 * @date 2019/12/6.
 */
public enum CouponStateEnum {
    /**
     * 用户优惠券使用状态
     */
    NOT_USED(0, "未使用"),
    ALREADY_USED(1, "已使用"),
    EXPIRED(2, "已过期");

    CouponStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }


    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
