package com.mtl.cypw.domain.payment.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Data
public class CmbConfigure {

    /**
     * 分行号
     */
    private String branchNo;

    /**
     * 支付商户号
     */
    private String merchantNo;

    /**
     * 秘钥
     */
    private String merchantKey;

    /**
     * 小程序商户号
     */
    private String corpNo;

    /**
     * 小程序商户密钥
     */
    private String secretKey;

    public Boolean checkParam() {
        if (StringUtils.isEmpty(branchNo) || StringUtils.isEmpty(merchantNo) || StringUtils.isEmpty(merchantKey)) {
            return false;
        }
        return true;
    }

}
