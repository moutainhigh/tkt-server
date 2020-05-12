package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.google.gson.Gson;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.param.RefundRequestParam;
import com.mtl.cypw.domain.payment.param.WechatPayRefundRequestParam;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
import com.mtl.cypw.payment.service.CmbRefundService;
import com.mtl.cypw.payment.service.WechatRefundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/2/25.
 */
@Slf4j
public class CmbTest extends BaseTest {


    @Resource
    CmbRefundService cmbRefundService;

    @Resource
    EnterprisePayTypeService enterprisePayTypeServiceImpl;

    private CmbConfigure getConfigure(Integer enterpriseId) throws BusinessException {
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.CMB_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            CmbConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), CmbConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    @Test
    public void refund() {
        RefundRequestParam param = new RefundRequestParam();
        param.setOrderNo("138292635496923136");
        param.setOrderRefundNo("808934");
        param.setRefundAmount(1L);
        CmbConfigure configure = getConfigure(5);
        String result = cmbRefundService.refund(param, configure);
        log.info("result:{}", JSONObject.toJSONString(result));
    }
}
