package com.mtl.cypw.payment.request.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/14.
 */
@Data
public class CmbPubKeyData {
    /**
     * 请求时间
     */
    private String dateTime;
    /**
     * 交易码,固定为“FBPK”
     */
    private String txCode;
    /**
     * 商户分行号
     */
    private String branchNo;
    /**
     * 商户号
     */
    private String merchantNo;
}
