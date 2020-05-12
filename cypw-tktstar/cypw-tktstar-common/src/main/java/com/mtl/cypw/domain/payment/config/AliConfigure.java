package com.mtl.cypw.domain.payment.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class AliConfigure {

    /**
     * 应用 id
     */
    private String appId;

    /**
     * 应用公钥
     */
    private String publicKey;
    /**
     * 应用私钥
     */
    private String privateKey;
    /**
     * 支付宝公钥
     */
    private String aliPublicKey;

    /**
     * 签名类型
     */
    private String signType = "RSA2";

    public Boolean checkParam() {
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(publicKey) || StringUtils.isEmpty(privateKey)) {
            return false;
        }
        return true;
    }
}
