package com.mtl.cypw.provider.stock.endpoint;

import com.google.common.collect.Lists;
import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.stock.endpoint.ReserveApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.stock.dto.ReserveFormDTO;
import com.mtl.cypw.domain.stock.dto.ReserveSeatDetailDTO;
import com.mtl.cypw.domain.stock.param.ReserveFormParam;
import com.mtl.cypw.domain.stock.param.ReserveQuerySpec;
import com.mtl.cypw.domain.stock.param.ReserveSeatParam;
import com.mtl.cypw.domain.stock.param.ReserveSeatQuerySpec;
import com.mtl.cypw.provider.stock.converter.ReserveConverter;
import com.mtl.cypw.provider.stock.converter.ReserveSeatDetailConverter;
import com.mtl.cypw.stock.exception.ReserveSeatException;
import com.mtl.cypw.stock.model.ReserveForm;
import com.mtl.cypw.stock.model.ReserveSeatDetail;
import com.mtl.cypw.stock.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 12:25
 */
@Slf4j
@RestController
public class ReserveEndpoint implements ReserveApi {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private ReserveConverter reserveConverter;

    @Autowired
    private ReserveSeatDetailConverter reserveSeatDetailConverter;

    @Override
    public TSingleResult<Integer> save(GenericRequest<ReserveFormParam> request) {
        ReserveFormParam param = request.getParam();
        try {
            return ResultBuilder.succTSingle(reserveService.saveReserveForm(param));
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_SHOW_RESERVE_SAVE_FAILED.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_SAVE_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_SAVE_FAILED.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> reserveSeats(GenericRequest<ReserveSeatParam> request) {
        ReserveSeatParam param = request.getParam();
        boolean result;
        try {
            result = reserveService.reserveSeats(param.getReservedId(), Lists.newArrayList(param.getSeatIds()));
        } catch (ReserveSeatException rse) {
            log.error(rse.getMessage(), rse);
            return ResultBuilder.failTSingle(rse.getCode(), rse.getMessage());
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getMsg());
        }
        if (!result) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getMsg());
        }
        return ResultBuilder.succTSingle(true);
    }

    @Override
    public TSingleResult<Boolean> cancelReserved(IdRequest idRequest) {
        Integer reserveId = Integer.valueOf(idRequest.getId());
        try {
            return ResultBuilder.succTSingle(reserveService.cancelReserved(reserveId));
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_SHOW_RESERVE_CANCEL_FAILED.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_CANCEL_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_CANCEL_FAILED.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> reserveTickets(GenericRequest<ReserveSeatParam> request) {
        ReserveSeatParam param = request.getParam();
        try {
            return ResultBuilder.succTSingle(reserveService.reserveTickets(param.getReservedId(), Lists.newArrayList(param.getSeatIds())));
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_SHOW_RESERVE_SEAT_TICKET_FAILED.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_SEAT_TICKET_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_SEAT_TICKET_FAILED.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> releaseReserveSeats(GenericRequest<ReserveSeatParam> request) {
        ReserveSeatParam param = request.getParam();
        try {
            return ResultBuilder.succTSingle(reserveService.releaseReserveSeats(param.getReservedId(), Lists.newArrayList(param.getSeatIds())));
        } catch (Exception e) {
            log.error(ErrorCode.ERROR_SHOW_RESERVE_SEAT_RELEASE_FAILED.getMsg(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_RESERVE_SEAT_RELEASE_FAILED.getCode(), ErrorCode.ERROR_SHOW_RESERVE_SEAT_RELEASE_FAILED.getMsg());
        }
    }

    @Override
    public TSingleResult<ReserveFormDTO> findById(IdRequest idRequest) {
        ReserveForm form = reserveService.findOne(Integer.valueOf(idRequest.getId()));
        return ResultBuilder.succTSingle(reserveConverter.convert(form));
    }

    @Override
    public TSingleResult<ReserveFormDTO> findBySerialNo(IdRequest idRequest) {
        ReserveForm form = reserveService.findOneByReservedNo(idRequest.getId());
        return ResultBuilder.succTSingle(reserveConverter.convert(form));
    }

    @Override
    public TPageResult<ReserveFormDTO> formPageQuery(QueryRequest<ReserveQuerySpec> request) {
        Pagination pagination = request.buildPagination();
        List<ReserveForm> forms = reserveService.findBySpec(request.getParam(), pagination);
        return ResultBuilder.succTPage(reserveConverter.batchConvert(forms), pagination);
    }

    @Override
    public TPageResult<ReserveSeatDetailDTO> seatDetailsPageQuery(QueryRequest<ReserveSeatQuerySpec> request) {
        Pagination pagination = request.buildPagination();
        List<ReserveSeatDetail> details = reserveService.findSeatDetailsBySpec(request.getParam(), pagination);
        return ResultBuilder.succTPage(reserveSeatDetailConverter.batchConvert(details), pagination);

    }
}
