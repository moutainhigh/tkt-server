package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/2/15.
 */
@Data
public class PayPalResult extends PayResult{

    public PayPalResult() {
        super.setPayType(PayTypeEnum.PAY_PAL);
    }

    private String jsonRequestData;
    private String rel;
    private String method;
}
