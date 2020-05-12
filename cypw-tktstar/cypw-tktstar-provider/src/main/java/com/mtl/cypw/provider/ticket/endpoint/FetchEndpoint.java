package com.mtl.cypw.provider.ticket.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.ticket.endpoint.FetchApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.ticket.dto.FetchPaperResultDTO;
import com.mtl.cypw.domain.ticket.dto.FetchRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.VoucherPaperDTO;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import com.mtl.cypw.provider.ticket.service.FetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-19 11:08
 */
@Slf4j
@RestController
public class FetchEndpoint implements FetchApi {

    @Autowired
    private FetchService fetchServiceImpl;

    @Override
    public TSingleResult<FetchPaperResultDTO> findOrderByCode(QueryRequest<FetchQueryParam> request) {
        FetchQueryParam param = request.getParam();
        try {
            FetchPaperResultDTO paperResultDTO = fetchServiceImpl.findOrderByFetchCode(param);
            if (!paperResultDTO.getSuccess()) {
                return ResultBuilder.failTSingle(paperResultDTO.getErrorCode(), paperResultDTO.getErrorMessage());
            }
            return ResultBuilder.succTSingle(paperResultDTO);
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_TICKET.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_TICKET.getCode(), ErrorCode.ERROR_TICKET.getMsg());
        }
    }

    @Override
    public TSingleResult<FetchPaperResultDTO> findOrderByIdCard(QueryRequest<FetchQueryParam> request) {
        return null;
    }

    @Override
    public TSingleResult<FetchPaperResultDTO> issue(GenericRequest<FetchCommandParam> request) {
        return null;
    }

    @Override
    public TSingleResult<FetchPaperResultDTO> ack(GenericRequest<FetchCommandParam> request) {
        return null;
    }

    @Override
    public TSingleResult<FetchPaperResultDTO> ackByGoods(GenericRequest<FetchCommandParam> request) {
        FetchCommandParam param = request.getParam();
        try {
            FetchPaperResultDTO paperResultDTO = fetchServiceImpl.ackByGoods(param);
            if (!paperResultDTO.getSuccess()) {
                return ResultBuilder.failTSingle(paperResultDTO.getErrorCode(), paperResultDTO.getErrorMessage());
            }
            return ResultBuilder.succTSingle(paperResultDTO);
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_TICKET.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_TICKET.getCode(), ErrorCode.ERROR_TICKET.getMsg());
        }
    }

    @Override
    public TPageResult<VoucherPaperDTO> recordQuery(QueryRequest<FetchQueryParam> request) {
        FetchQueryParam param = request.getParam();
        Pagination pagination = request.buildPagination();
        FetchRecordQueryDTO dto = fetchServiceImpl.findFetchRecordByQuery(param, pagination);
        return ResultBuilder.succTPage(dto.getVoucherPapers(), pagination);
    }

    @Override
    public TSingleResult<Integer> countQuery(QueryRequest<FetchQueryParam> request) {
        FetchQueryParam param = request.getParam();
        FetchRecordQueryDTO dto = fetchServiceImpl.findFetchRecordByQuery(param, request.buildPagination());
        return ResultBuilder.succTSingle(dto.getFetchCount());
    }
}
