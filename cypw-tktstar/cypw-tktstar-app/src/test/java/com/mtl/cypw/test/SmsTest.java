package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.cypw.mpm.model.SmsMessage;
import com.mtl.cypw.mpm.service.SmsMessageService;
import com.mtl.cypw.mpm.service.SmsService;
import com.juqitech.response.paging.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mtl.cypw.domain.mpm.constant.SmsTemplate.SMS_USER_VERIFY_CODE;

/**
 * @author tang.
 * @date 2019/12/26.
 */
@Slf4j
public class SmsTest extends BaseTest {

    @Autowired
    SmsService smsServiceImpl;

    @Autowired
    SmsMessageService smsMessageServiceImpl;

    @Test
    public void sendSmsTest() {
        SmsSendParam param = new SmsSendParam();
        String verifyCode = (int) (Math.random() * 9000 + 1000) + "";
        Map<String, String> variables = new HashMap<>();
        variables.put("VERIFY_CODE", verifyCode);
        param.setVariables(variables);
        param.setTemplateCode(SMS_USER_VERIFY_CODE);
        param.setProduct("CYPW");
        param.setCountryCode(86);
        param.setCellphone("18321407297");
        param.setEnterpriseId(1);
        smsServiceImpl.sendSms(param);
    }

    @Test
    public void getSmsMessage() {
        smsMessageServiceImpl.updateFailCount(1);
        SmsMessageQueryParam queryParam = new SmsMessageQueryParam();
        queryParam.setCellphone("18321407297");
        queryParam.setEnterpriseId(1);
        queryParam.setLessFailCount(2);
        Pagination pagination = new Pagination(1,10);
        List<SmsMessage> message = smsMessageServiceImpl.searchSmsMessage(queryParam);
        log.info("message:{}", JSONObject.toJSONString(message));

        List<SmsMessage> message1 = smsMessageServiceImpl.searchSmsMessagePage(queryParam,pagination);
        log.info("message1:{}", JSONObject.toJSONString(message1),JSONObject.toJSONString(pagination));
    }
}
