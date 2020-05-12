package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.google.gson.Gson;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.enums.WechatPayTypeEnum;
import com.mtl.cypw.domain.payment.param.WechatPayRefundRequestParam;
import com.mtl.cypw.domain.payment.param.WechatPayRequestParam;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
import com.mtl.cypw.payment.service.WechatPayService;
import com.mtl.cypw.payment.service.WechatRefundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/8.
 */
@Slf4j
public class WxPayTest extends BaseTest {

    @Resource
    WechatPayService wechatPayService;

    @Resource
    WechatRefundService wechatRefundService;

    @Resource
    EnterprisePayTypeService enterprisePayTypeServiceImpl;

    String wxNotifyUrl = "https://yuyuandev.fhlun.com/auth/notify";


    @Test
    public void pay() {
        WechatPayRequestParam param = new WechatPayRequestParam();
        param.setEnterpriseId(7);
        param.setPayTypeEnum(PayTypeEnum.WECHAT_PAY);
        param.setOrderNo("888888999999");
        param.setOrderAmount(1L);
        param.setRemoteAddress("127.0.0.1");
        param.setNotifyUrl(wxNotifyUrl);
        param.setOpenid("o1s-Uw8cTrdfRr1iYf7cpiwM5cOg");
        param.setWechatPayTypeEnum(WechatPayTypeEnum.NATIVE);
        WxConfigure configure = getConfigure(7);
        WxPayUnifiedOrderResult payResult = wechatPayService.unifiedOrder(param, configure);
        log.info("result:{}", payResult);
    }


    private WxConfigure getConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.WECHAT_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            WxConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), WxConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("微信支付，商户配置错误,enterpriseId:{},config:{}", enterpriseId, JSONObject.toJSONString(enterprisePayType.getConfigJson()));
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    @Test
    public void refund() {
        WechatPayRefundRequestParam param = new WechatPayRefundRequestParam();
        param.setOrderNo("152480597197504512");
        param.setOutRefundNo("942_1155");
        param.setRefundAmount(1L);
        param.setRefundFee(1);
        param.setNotifyUrl("https://yuyuandev.fhlun.com/api/pub/v1/notify/wx");
        WxConfigure configure = getConfigure(1);
        configure.setCertLocalPath("D:\\opt\\cypw_configs\\cert\\wx\\caiyi\\apiclient_cert.p12");
        WxPayRefundResult result = wechatRefundService.refund(param, configure);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

}
