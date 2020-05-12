package com.mtl.cypw.domain.payment.param;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/23.
 */
@Data
public class WechatPayRefundRequestParam extends RefundRequestParam {

    /**
     * 退款单号
     */
    private String outRefundNo;

    /**
     * 退款金额(分)
     */
    private Integer refundFee;

    @Override
    public boolean checkParam() {
        if (StringUtils.isEmpty(outRefundNo) || refundFee == null) {
            return false;
        }
        return true;
    }
}
