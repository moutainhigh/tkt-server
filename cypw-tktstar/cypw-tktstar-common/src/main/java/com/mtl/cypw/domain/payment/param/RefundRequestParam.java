package com.mtl.cypw.domain.payment.param;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author tang.
 * @date 2020/2/25.
 */
@Data
public class RefundRequestParam {
    /**
     * 商户id
     */
    private Integer enterpriseId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 退款编号，系统自动生成
     */
    private String orderRefundNo;
    /**
     * 订单明细Id
     * 空值时按订单退款，有值时按明细退款
     */
    private List<Integer> orderItemIds;
    /**
     * 退款金额（分）
     */
    private Long refundAmount;
    /**
     * 退款通知地址回调地址
     */
    private String notifyUrl;

    public boolean checkParam() {
        if (this.enterpriseId == null || StringUtils.isEmpty(orderNo)) {
            return false;
        }
        return true;
    }
}
