package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.mpm.dto.SmsMessageDTO;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2019/12/30.
 */
public interface SmsMessageApi {

    /**
     * 修改发送失败次数
     *
     * @param request
     */
    @RequestMapping(value = "/endpoint/v1/mpm/sms_message/update", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> updateFailCount(@RequestBody IdRequest request);

    /**
     * 查询单条发送记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/sms_message/get", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<SmsMessageDTO> getSmsMessage(@RequestBody IdRequest request);

    /**
     * 查询发送短信列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/sms_message/messages", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TMultiResult<SmsMessageDTO> searchSmsMessage(@RequestBody QueryRequest<SmsMessageQueryParam> request);

    /**
     * 分页查询发送短信列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/sms_message/messages_page", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TPageResult<SmsMessageDTO> searchSmsMessagePage(@RequestBody QueryRequest<SmsMessageQueryParam> request);
}
