package com.mtl.cypw.domain.payment.enums;

/**
 * @author tang.
 * @date 2019/12/3.
 */
public enum PayTypeEnum {
    /**
     * 支付宝支付
     */
    ALI_PAY(110, "支付宝支付"),
    /**
     * 微信支付
     */
    WECHAT_PAY(111, "微信支付"),
    /**
     * 招行支付
     */
    CMB_PAY(116, "招行支付"),
    /**
     * 建行支付
     */
    CCB_PAY(117, "建行分期支付"),
    /**
     * 建行支付
     */
    PAY_PAL(118, "PayPal支付"),
    /**
     * 零元支付
     */
    ZERO_PAY(99, "零元支付");

    private int payCode;

    private String payName;

    PayTypeEnum(int payCode, String payName) {
        this.payCode = payCode;
        this.payName = payName;
    }

    public static PayTypeEnum getObject(int code) {
        for (PayTypeEnum payTypeEnum : values()) {
            if (payTypeEnum.getPayCode() == code) {
                return payTypeEnum;
            }
        }
        return null;
    }

    public int getPayCode() {
        return payCode;
    }

    public String getPayName() {
        return payName;
    }
}
