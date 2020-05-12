package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Data
public class PayRequestParam {

    /**
     * 商户id
     */
    private Integer enterpriseId;
    /**
     * 支付类型
     */
    private PayTypeEnum payTypeEnum;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单金额（分）
     */
    private Long orderAmount;
    /**
     * 回调地址
     */
    private String notifyUrl;

    public boolean checkParam() {
        if (this.enterpriseId == null || payTypeEnum == null || StringUtils.isEmpty(orderNo) || orderAmount == null) {
            return false;
        }
        return true;
    }
}
