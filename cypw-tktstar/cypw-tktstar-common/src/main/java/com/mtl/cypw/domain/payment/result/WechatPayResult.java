package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Data
public class WechatPayResult extends PayResult {

    public WechatPayResult(){
        super.setPayType(PayTypeEnum.WECHAT_PAY);
    }

    private String codeUrl;
    private String mwebUrl;
    private String appId;
    private String nonceStr;
    private String timeStamp;
    private String prepayId;
    private String signType;
    private String sign;
}
