package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.mpm.endpoint.SmsMessageApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.mpm.dto.SmsMessageDTO;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import com.mtl.cypw.mpm.model.SmsMessage;
import com.mtl.cypw.mpm.service.SmsMessageService;
import com.mtl.cypw.provider.mpm.converter.SmsMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Slf4j
@RestController
public class SmsMessageEndpoint implements SmsMessageApi {

    @Autowired
    SmsMessageService smsMessageServiceImpl;

    @Resource
    SmsMessageConverter converter;

    @Override
    public TSingleResult<Boolean> updateFailCount(IdRequest request) {
        if (request == null || request.getId() == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        smsMessageServiceImpl.updateFailCount(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(true);
    }

    @Override
    public TSingleResult<SmsMessageDTO> getSmsMessage(IdRequest request) {
        if (request == null || request.getId() == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        SmsMessage message = smsMessageServiceImpl.getSmsMessage(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(converter.toDto(message));
    }

    @Override
    public TMultiResult<SmsMessageDTO> searchSmsMessage(QueryRequest<SmsMessageQueryParam> request) {
        List<SmsMessage> messages = smsMessageServiceImpl.searchSmsMessage(request.getParam());
        return ResultBuilder.succTMulti(converter.toDto(messages));
    }

    @Override
    public TPageResult<SmsMessageDTO> searchSmsMessagePage(QueryRequest<SmsMessageQueryParam> request) {
        Pagination pagination = request.buildPagination();
        List<SmsMessage> messages = smsMessageServiceImpl.searchSmsMessagePage(request.getParam(), pagination);
        return ResultBuilder.succTPage(converter.toDto(messages), pagination);
    }

}
