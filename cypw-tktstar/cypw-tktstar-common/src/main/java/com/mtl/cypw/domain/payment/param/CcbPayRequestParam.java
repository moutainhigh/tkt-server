package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.CcbPayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/7.
 */
@Data
public class CcbPayRequestParam extends PayRequestParam {

    /**
     * 支付类型
     */
    private CcbPayTypeEnum ccbPayTypeEnum;

    /**
     * 分期期数
     */
    private String installment;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 商户URL
     * 非必填
     */
    private String referer;
}
