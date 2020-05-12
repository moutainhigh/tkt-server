package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.PayPalPayIntentEnum;
import com.mtl.cypw.domain.payment.enums.PayPalPayTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@Data
public class PayPalRequestParam extends PayRequestParam {

    /**
     * 支付类型
     */
    private PayPalPayTypeEnum payPalPayTypeEnum;

    /**
     * PayPal交易类型
     */
    private PayPalPayIntentEnum payPalPayIntentEnum;

    /**
     * PayPal回调执行支付地址
     */
    private String returnUrl;

    /**
     * 取消支付PayPal重定向地址
     */
    private String cancelUrl;


    @Override
    public boolean checkParam() {
        if (!super.checkParam() || this.payPalPayTypeEnum == null || this.payPalPayIntentEnum == null || StringUtils.isEmpty(this.returnUrl)) {
            return false;
        }
        return true;
    }
}
