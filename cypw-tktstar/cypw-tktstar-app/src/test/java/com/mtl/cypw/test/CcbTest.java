package com.mtl.cypw.test;

import com.mtl.cypw.common.utils.MD5Util;
import com.mtl.cypw.domain.payment.callback.CcbCallbackParam;
import com.mtl.cypw.domain.payment.config.CcbConfigure;
import com.mtl.cypw.payment.request.CcbPayRequest;
import com.mtl.cypw.payment.service.CcbPayService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/7.
 */
@Slf4j
public class CcbTest extends BaseTest {

    @Resource
    CcbPayService ccbPayService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    public static void main(String[] args) {
        CcbPayRequest data = new CcbPayRequest();
        data.setMERCHANTID("123");
        data.setPOSID("456");
        data.setBRANCHID("789");
        data.setORDERID("888888");
        data.setPAYMENT("0.01");
        data.setPUB("123456789012345678901234567890");
        data.setCLIENTIP("128.128.80.125");
        log.info("str:{}", data.toString());
        log.info("mac:{}", MD5Util.md5(data.toString()));
    }

    @Test
    public void checkSign() {
        CcbCallbackParam param = new CcbCallbackParam();
        param.setPosId("042946867");
        param.setBranchId("310000000");
        param.setOrderId("150619572902871040");
        param.setPayment("0.02");
        param.setCurCode("01");
        param.setSuccess("Y");
        param.setType("1");
        param.setClientIp("101.87.201.104");
        param.setAccDate("20200220");
        param.setInstallNum("3");
        param.setSign("3cb380ec742ef584e39a79e94f8be3dc325b2c97106a71fd7115bdf6ee57e23bcd663f7ec1bb6a586937ace1f2c529c9431028ad8257dd364a7bfa16a536564c7578e79635c83293766359b58db6db35b3256d5f4ed207998b287b57f053a4f1b94b3ea954a3730c639f72cc1836a3a1b16a1badac406f59ebe07a6e4f25ffcc");
        CcbConfigure configure = enterpriseConfigService.getCcbConfigure(7);
        ccbPayService.checkSign(param, configure);
    }

}
