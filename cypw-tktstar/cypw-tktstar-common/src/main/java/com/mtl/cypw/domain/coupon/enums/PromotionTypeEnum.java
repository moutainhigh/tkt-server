package com.mtl.cypw.domain.coupon.enums;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public enum PromotionTypeEnum {

    /**
     * 优惠活动
     */
    OFFLINE(0, "线下优惠"),
    DISCOUNT_COUPON(1, "折扣券"),
    CASH_COUPON(2, "现金券"),
    EXCHANGE_COUPON(3, "兑换券");

    private int code;
    private String name;

    PromotionTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PromotionTypeEnum getObject(int code) {
        for (PromotionTypeEnum promotionTypeEnum : values()) {
            if (promotionTypeEnum.getCode() == code) {
                return promotionTypeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
