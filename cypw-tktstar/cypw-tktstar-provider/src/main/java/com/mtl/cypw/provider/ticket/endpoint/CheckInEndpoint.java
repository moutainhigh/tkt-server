package com.mtl.cypw.provider.ticket.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.ticket.endpoint.CheckInApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.ticket.dto.CheckRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.domain.ticket.param.CheckInTicketParam;
import com.mtl.cypw.provider.ticket.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-15 10:16
 */
@Slf4j
@RestController
public class CheckInEndpoint implements CheckInApi {

    @Autowired
    private CheckService checkServiceImpl;

    @Override
    public TSingleResult<TicketPaperResultDTO> findTicketByCode(GenericRequest<CheckInQueryParam> request) {
        CheckInQueryParam param = request.getParam();
        TicketPaperResultDTO paperResultDTO = null;
        try {
            paperResultDTO = checkServiceImpl.findTicketByCheckCode(param);
            if (!paperResultDTO.getSuccess()) {
                return ResultBuilder.failTSingle(paperResultDTO.getErrorCode(), paperResultDTO.getErrorMessage());
            }
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_TICKET.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_TICKET.getCode(), ErrorCode.ERROR_TICKET.getMsg());
        }
        return ResultBuilder.succTSingle(paperResultDTO);
    }

    @Override
    public TSingleResult<TicketPaperResultDTO> findTicketByMobile(GenericRequest<CheckInQueryParam> request) {
        CheckInQueryParam param = request.getParam();
        try {
            TicketPaperResultDTO paperResultDTO = checkServiceImpl.findTicketByMobileNo(param);
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
    public TSingleResult<TicketPaperResultDTO> consume(GenericRequest<CheckInTicketParam> request) {
        CheckInTicketParam param = request.getParam();
        try {
            TicketPaperResultDTO paperResultDTO = checkServiceImpl.consume(param);
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
    public TPageResult<TicketPaperDTO> recordQuery(QueryRequest<CheckInQueryParam> request) {
        CheckInQueryParam param = request.getParam();
        Pagination pagination = request.buildPagination();
        CheckRecordQueryDTO dto = checkServiceImpl.findCheckRecordByQuery(param, pagination);
        return ResultBuilder.succTPage(dto.getTicketPapers(), pagination);
    }

    @Override
    public TSingleResult<Integer> countQuery(QueryRequest<CheckInQueryParam> request) {
        CheckInQueryParam param = request.getParam();
        CheckRecordQueryDTO dto = checkServiceImpl.findCheckRecordByQuery(param, request.buildPagination());
        return ResultBuilder.succTSingle(dto.getCheckCount());
    }


}
