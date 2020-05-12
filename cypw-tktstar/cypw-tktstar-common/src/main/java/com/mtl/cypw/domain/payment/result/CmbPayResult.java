package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/31.
 */
@Data
public class CmbPayResult extends PayResult {

    public CmbPayResult() {
        super.setPayType(PayTypeEnum.CMB_PAY);
    }

    private String gatewayUrl;
    private String charset = "UTF-8";
    private String jsonRequestData;

}
