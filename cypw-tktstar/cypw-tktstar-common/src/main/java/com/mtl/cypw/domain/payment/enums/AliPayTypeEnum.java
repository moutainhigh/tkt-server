package com.mtl.cypw.domain.payment.enums;

/**
 * @author tang.
 * @date 2019/12/3.
 */
public enum AliPayTypeEnum {
    /**
     * 手机网页支付
     */
    WAP("alipay.trade.wap.pay"),
    /**
     * app支付
     */
    APP("alipay.trade.app.pay"),
    /**
     * PC场景下单支付
     */
    PAGE("alipay.trade.page.pay");

    private String gateway;

    AliPayTypeEnum(String gateway) {
        this.gateway = gateway;
    }

    public String getGateway() {
        return gateway;
    }
}
