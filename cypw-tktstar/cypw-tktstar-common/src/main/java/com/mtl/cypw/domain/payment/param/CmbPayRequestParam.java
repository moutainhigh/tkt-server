package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.CmbPayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Data
public class CmbPayRequestParam extends PayRequestParam {

    /**
     * 支付类型
     */
    private CmbPayTypeEnum cmbPayTypeEnum;

    /**
     * 返回商户页面的网址
     */
    private String quitUrl;

    /**
     * 过期时间，单位分钟
     * 默认30分钟
     */
    private String expireTimeSpan = "30";

    /**
     * 签名方式
     * 默认SHA-256
     */
    private String signType = "SHA-256";

    @Override
    public boolean checkParam() {
        if (!super.checkParam() || this.cmbPayTypeEnum == null) {
            return false;
        }
        return true;
    }
}
