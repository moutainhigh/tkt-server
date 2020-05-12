package com.mtl.cypw.provider.ticket.service.impl;

import com.google.common.collect.Lists;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.ticket.dto.FetchPaperResultDTO;
import com.mtl.cypw.domain.ticket.dto.FetchRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.OrderPaperDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import com.mtl.cypw.provider.ticket.converter.VoucherPaperConverter;
import com.mtl.cypw.provider.ticket.service.FetchService;
import com.mtl.cypw.provider.ticket.service.FetchVerifierService;
import com.mtl.cypw.ticket.exception.FetchBizException;
import com.mtl.cypw.ticket.model.FetchVoucher;
import com.mtl.cypw.ticket.service.FetchVoucherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-19 11:19
 */
@Slf4j
@Service
public class FetchServiceImpl implements FetchService {

    @Autowired
    private FetchVoucherService fetchVoucherServiceImpl;

    @Autowired
    private FetchVerifierService fetchVerifierServiceImpl;

    @Autowired
    private VoucherPaperConverter voucherPaperConverter;


    @Override
    public FetchPaperResultDTO findOrderByFetchCode(FetchQueryParam param) {
        FetchPaperResultDTO result = new FetchPaperResultDTO();
        result.setFetchCode(param.getFetchCode());
        result.setFetchMethod(param.getFetchMethod());
        try {
            OrderPaperDTO paper = fetchVerifierServiceImpl.verificationByCode(param);
            result.setSuccess(true);
            result.setOrderPapers(Lists.newArrayList(paper));
            return result;
        } catch (FetchBizException fbz) {
            result.setSuccess(false);
            result.setErrorCode(fbz.getCode());
            result.setErrorMessage(fbz.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.ERROR_TICKET.getCode());
            result.setErrorMessage(ErrorCode.ERROR_TICKET.getMsg());
        }
        return result;
    }

    @Override
    public TicketPaperResultDTO findOrderByIDCard(FetchQueryParam param) {
        return null;
    }

    @Override
    public FetchPaperResultDTO ackByGoods(FetchCommandParam param) {
        FetchPaperResultDTO result = new FetchPaperResultDTO();
        result.setFetchCode(param.getFetchCode());
        result.setFetchMethod(param.getFetchMethod());
        try {
            OrderPaperDTO paper = fetchVerifierServiceImpl.verificationByCode(param);
            FetchVoucher voucher = fetchVoucherServiceImpl.fetchGoodsAck(paper, param);
            result.setSuccess(true);
            result.setVoucherNo(voucher.getVoucherNo());
        } catch (FetchBizException fbz) {
            result.setSuccess(false);
            result.setErrorCode(fbz.getCode());
            result.setErrorMessage(fbz.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.ERROR_TICKET.getCode());
            result.setErrorMessage(ErrorCode.ERROR_TICKET.getMsg());
        }
        return result;
    }

    @Override
    public FetchRecordQueryDTO findFetchRecordByQuery(FetchQueryParam param, Pagination pagination) {
        FetchRecordQueryDTO queryResult = new FetchRecordQueryDTO();
        if (param.getFetchDate() != null) {
            queryResult.setFetchDate(param.getFetchDate());
        }
        List<FetchVoucher> vouchers = fetchVoucherServiceImpl.findVoucherByQuery(param, pagination);
        if (CollectionUtils.isEmpty(vouchers)) {
            queryResult.setFetchCount(0);
            queryResult.setVoucherPapers(Collections.emptyList());
        } else {
            queryResult.setFetchCount(CollectionUtils.size(vouchers));
            queryResult.setVoucherPapers(voucherPaperConverter.batchConvert(vouchers));
        }
        return queryResult;
    }
}
