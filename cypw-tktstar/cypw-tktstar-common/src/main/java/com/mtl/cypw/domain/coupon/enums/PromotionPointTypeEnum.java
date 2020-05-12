package com.mtl.cypw.domain.coupon.enums;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public enum PromotionPointTypeEnum {

    /**
     * 优惠限制
     */
    ALL(0, "全部"),
    POINT_PROGRAM_TYPE(1, "指定演出类型"),
    POINT_PROGRAM_ID(2, "指定演出");

    private int code;
    private String name;

    PromotionPointTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
