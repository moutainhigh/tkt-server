package com.mtl.cypw.domain.payment.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/1/7.
 */
@Data
public class CcbConfigure {

    /**
     * 商户代码
     * 由建行统一分配
     */
    private String merchantId;
    /**
     * 商户柜台代码
     * 由建行统一分配
     */
    private String posId;
    /**
     * 分行代码
     * 由建行统一分配
     */
    private String branchId;

    /**
     * 公钥
     * 由建行统一分配
     */
    private String pubKey;

    public Boolean checkParam() {
        //加签时需要获取公钥后30位
        int minKeyNum = 30;
        if (StringUtils.isEmpty(merchantId) || StringUtils.isEmpty(posId) || StringUtils.isEmpty(branchId) || StringUtils.isEmpty(pubKey) || pubKey.length() < minKeyNum) {
            return false;
        }
        return true;
    }
}
