package com.mtl.cypw.payment.constant;

/**
 * @author tang.
 * @date 2019/12/31.
 */
public class CmbConstants {

    private final static String releaseEnv = "release";

    public static String getGatewayUrl_H5(String env) {
        if (releaseEnv.equalsIgnoreCase(env)) {
            return "https://netpay.cmbchina.com/netpayment/BaseHttp.dll?MB_EUserPay";
        }
        return "http://121.15.180.66:801/netpayment/BaseHttp.dll?MB_EUserPay";
    }

    public static String getGatewayUrl_APP(String env) {
        if (releaseEnv.equalsIgnoreCase(env)) {
            return "https://netpay.cmbchina.com/netpayment/BaseHttp.dll?MB_APPPay";
        }
        return "http://121.15.180.66:801/netpayment/BaseHttp.dll?MB_APPPay";
    }

    public static String getRefundUrl(String env) {
        if (releaseEnv.equalsIgnoreCase(env)) {
            return "https://payment.ebank.cmbchina.com/NetPayment/BaseHttp.dll?DoRefundV2";
        }
        return "http://121.15.180.66:801/NetPayment_dl/BaseHttp.dll?DoRefundV2";
    }

    public static String getPubKeyUrl(String env) {
        if (releaseEnv.equalsIgnoreCase(env)) {
            return "https://b2b.cmbchina.com/CmbBank_B2B/UI/NetPay/DoBusiness.ashx";
        }
        return "http://121.15.180.72/CmbBank_B2B/UI/NetPay/DoBusiness.ashx";
    }

    public static String getOrderUrl(String env) {
        if (releaseEnv.equalsIgnoreCase(env)) {
            return "https://payment.ebank.cmbchina.com/NetPayment/BaseHttp.dll?QuerySingleOrder";
        }
        return "http://121.15.180.66:801/Netpayment_dl/BaseHttp.dll?QuerySingleOrder";
    }
}
