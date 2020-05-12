package com.mtl.cypw.domain.payment.param;

import com.mtl.cypw.domain.payment.enums.WechatPayTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Data
public class WechatPayRequestParam extends PayRequestParam {

    private WechatPayTypeEnum wechatPayTypeEnum;

    private String remoteAddress;

    private String openid;

    /**
     * H5支付时必填
     */
    private String sceneInfo;

    /**
     * 商品描述
     */
    private String product;

    @Override
    public boolean checkParam() {
        if (!super.checkParam() || this.wechatPayTypeEnum == null || StringUtils.isEmpty(remoteAddress) || StringUtils.isEmpty(product)) {
            return false;
        }
        if (wechatPayTypeEnum.equals(WechatPayTypeEnum.JSAPI) && StringUtils.isEmpty(openid)) {
            return false;
        } else if (wechatPayTypeEnum.equals(WechatPayTypeEnum.MWEB) && StringUtils.isEmpty(sceneInfo)) {
            return false;
        }
        return true;
    }
}
