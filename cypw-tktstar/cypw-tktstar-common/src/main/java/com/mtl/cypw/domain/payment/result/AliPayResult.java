package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class AliPayResult extends PayResult {

    public AliPayResult(){
        super.setPayType(PayTypeEnum.ALI_PAY);
    }

    private String body;
}
