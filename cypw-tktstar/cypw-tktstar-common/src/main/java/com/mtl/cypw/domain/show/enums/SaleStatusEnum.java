package com.mtl.cypw.domain.show.enums;

/**
 * @author tang.
 * @date 2019/12/17.
 */
public enum SaleStatusEnum {

    /**
     * 销售类型
     */
    STOP_SALE(70, "停售"),
    START_SALE(71, "开售"),
    ADVANCE_SALE(72, "预售"),
    APPOINTMENT_SALE(73, "预约");

    SaleStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SaleStatusEnum getObject(int code) {
        for (SaleStatusEnum saleStatusEnum : values()) {
            if (saleStatusEnum.getCode() == code) {
                return saleStatusEnum;
            }
        }
        return null;
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
