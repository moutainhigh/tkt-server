package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2019/12/16.
 */
public interface SmsApi {

    /**
     * 发送短信
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/send/sms", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> sendSms(@RequestBody GenericRequest<SmsSendParam> request);
}
