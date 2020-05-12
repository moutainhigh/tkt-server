package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.AliPayTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class AliPayRequestParam extends PayRequestParam {

    private String subject;

    private AliPayTypeEnum aliPayTypeEnum;

    /**
     * 返回商户页面的网址
     */
    private String quitUrl;

    @Override
    public boolean checkParam() {
        if (!super.checkParam() || this.aliPayTypeEnum == null || StringUtils.isEmpty(subject)) {
            return false;
        }
        return true;
    }
}
