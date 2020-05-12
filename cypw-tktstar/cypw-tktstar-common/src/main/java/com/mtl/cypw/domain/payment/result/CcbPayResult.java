package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/8.
 */
@Data
public class CcbPayResult extends PayResult {

    public CcbPayResult() {
        super.setPayType(PayTypeEnum.CCB_PAY);
    }

    private String jsonRequestData;
}
