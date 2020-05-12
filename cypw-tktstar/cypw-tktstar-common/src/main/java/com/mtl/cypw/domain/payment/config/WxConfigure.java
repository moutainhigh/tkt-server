package com.mtl.cypw.domain.payment.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang
 * @date 2019/10/10.
 */
@Data
public class WxConfigure {

    /**
     * 终端设备号(门店号或收银设备ID)，默认请传"WEB"
     */
    public static final String DEVICEINFO = "WEB";

    /**
     * 货币类型默认人民币
     */
    public static final String FEETYPE = "CNY";

    /**
     * 交易类型
     * 1.JSAPI--公众号支付
     * 2.NATIVE--原生扫码支付
     * 3.APP--app支付，统一下单接口
     * 4.MWEB--H5支付
     * 5.MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    public interface TRADE_TYPE {

        String JSAPI = "JSAPI";
        String NATIVE = "NATIVE";
        String APP = "APP";
        String MWEB = "MWEB";
        String MICROPAY = "MICROPAY";
    }

    private String key;

    private String appId;

    private String mchId;

    private String certLocalPath;

    private String certPassword;

    private String secret;

    public Boolean checkParam() {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(appId) || StringUtils.isEmpty(mchId) || StringUtils.isEmpty(secret)) {
            return false;
        }
        return true;
    }
}
