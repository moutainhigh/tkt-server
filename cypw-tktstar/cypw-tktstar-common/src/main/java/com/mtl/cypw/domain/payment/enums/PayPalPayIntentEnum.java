package com.mtl.cypw.domain.payment.enums;

/**
 * PayPal交易类型
 *
 * @author tang.
 * @date 2020/2/14.
 */
public enum PayPalPayIntentEnum {
    /**
     * 适用于马上发货的商品，如虚拟物品销售，这类网站集成EC，通常选sale
     */
    sale,
    /**
     * 买家付款后需要你进行确认，然后钱才进你的pp账户
     */
    authorize,
    /**
     * 普通物品销售，买家一付款，钱就到你账户，不需确认
     */
    order
}
