package com.mtl.cypw.api.msg.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.msg.param.MsgSendParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2020/3/6.
 */
public interface MsgSendApi {

    /**
     * 发送消息
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/msg/send", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> sendMsg(@RequestBody GenericRequest<MsgSendParam> request);
}
