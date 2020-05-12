package com.mtl.cypw.payment.constant;

/**
 * @author tang.
 * @date 2020/2/14.
 */
public class PaymentConstants {

    public static String getPayPalConfigKey(String paymentId) {
        return "PAYPAL_CONFIG_" + paymentId ;
    }
}
