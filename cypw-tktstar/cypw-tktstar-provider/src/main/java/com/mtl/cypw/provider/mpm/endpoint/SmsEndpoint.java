package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.SmsApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.cypw.mpm.service.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tang.
 * @date 2019/12/16.
 */
@RestController
public class SmsEndpoint implements SmsApi {

    @Autowired
    SmsService smsServiceImpl;

    @Override
    public TSingleResult<Boolean> sendSms(GenericRequest<SmsSendParam> request) {
        if (request.getParam() == null || StringUtils.isEmpty(request.getParam().getCellphone())) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_MEMBER_PARAMETER.getCode(), ErrorCode.ERROR_MEMBER_PARAMETER.getMsg());
        }
        return smsServiceImpl.sendSms(request.getParam());
    }
}
