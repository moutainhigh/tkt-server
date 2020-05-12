package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.notification.param.SmsParam;
import org.springframework.stereotype.Component;

/**
 * @author tang.
 * @date 2019/12/19.
 */
@Component
public class SmsParamConverter {
    public SmsParam toParam(SmsSendParam pojo) {
        if (pojo == null) {
            return null;
        }
        SmsParam param = new SmsParam();
        param.setProductChannelId(pojo.getProductChannelId());
        param.setProduct(pojo.getProduct());
        param.setChannel(pojo.getChannel());
        param.setTemplateCode(pojo.getTemplateCode());
        param.setBizId(pojo.getBizId());
        param.setVariables(pojo.getVariables());
        param.setContent(pojo.getContent());
        param.setCountryCode(pojo.getCountryCode());
        param.setCellphone(pojo.getCellphone());
        return param;
    }
}
