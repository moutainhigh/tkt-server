package com.mtl.cypw.provider.ticket.service.impl;

import com.google.common.collect.Lists;
import com.juqitech.response.paging.Pagination;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.ticket.dto.CheckRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.CheckInDeviceParam;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.domain.ticket.param.CheckInTicketParam;
import com.mtl.cypw.provider.ticket.converter.CheckPaperConverter;
import com.mtl.cypw.provider.ticket.service.CheckService;
import com.mtl.cypw.provider.ticket.service.CheckVerifierService;
import com.mtl.cypw.ticket.exception.TicketBizException;
import com.mtl.cypw.ticket.model.CheckRecord;
import com.mtl.cypw.ticket.service.CheckRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 23:51
 */
@Slf4j
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckRecordService checkRecordServiceImpl;

    @Autowired
    private CheckVerifierService checkVerifierService;

    @Autowired
    private CheckPaperConverter checkPaperConverter;

    @Override
    public TicketPaperResultDTO findTicketByCheckCode(CheckInQueryParam param) {
        TicketPaperResultDTO result = new TicketPaperResultDTO();
        result.setCheckCode(param.getCheckCode());
        try {
            TicketPaperDTO paper = checkVerifierService.verificationByCode(param.getCheckCode(), param.getCheckUserId());
            if (!paper.getPassed()) {
                CheckRecord checkRecord = checkRecordServiceImpl.findLastCheckRecordByTicketId(paper.getTicketId());
                paper.setCheckUserId(checkRecord.getCheckUserId());
                paper.setCheckUserName(checkRecord.getCheckUserName());
                paper.setCheckEntry(checkRecord.getCheckEntry());
                paper.setCheckTime(checkRecord.getCheckTime());
                paper.setChannel(checkRecord.getChannel());
                paper.setCheckMethod(checkRecord.getCheckMethod());
            }
            result.setSuccess(true);
            result.setTicketPapers(Lists.newArrayList(paper));
            return result;
        } catch (TicketBizException tbz) {
            result.setSuccess(false);
            result.setErrorCode(tbz.getCode());
            result.setErrorMessage(tbz.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.ERROR_TICKET.getCode());
            result.setErrorMessage(ErrorCode.ERROR_TICKET.getMsg());
        }
        return result;
    }

    @Override
    public TicketPaperResultDTO findTicketByMobileNo(CheckInQueryParam param) {
        TicketPaperResultDTO result = new TicketPaperResultDTO();
        result.setMobileNo(param.getMobileNo());
        try {
            List<TicketPaperDTO> paper = checkVerifierService.verificationByMobileNo(param.getMobileNo(), param.getCheckUserId());
            result.setSuccess(true);
            result.setTicketPapers(Lists.newArrayList(paper));
            return result;
        } catch (TicketBizException tbz) {
            result.setSuccess(false);
            result.setErrorCode(tbz.getCode());
            result.setErrorMessage(tbz.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.ERROR_TICKET.getCode());
            result.setErrorMessage(ErrorCode.ERROR_TICKET.getMsg());
        }
        return result;
    }

    @Override

    public TicketPaperResultDTO consume(CheckInTicketParam param) {
        TicketPaperResultDTO result = new TicketPaperResultDTO();
        result.setCheckCode(param.getCheckCode());
        TicketPaperDTO paper = null;
        try {
            paper = checkVerifierService.verificationByCode(param.getCheckCode(), param.getCheckUserId());
        } catch (TicketBizException tbz) {
            result.setSuccess(false);
            result.setErrorCode(tbz.getCode());
            result.setErrorMessage(tbz.getMessage());
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.ERROR_TICKET.getCode());
            result.setErrorMessage(ErrorCode.ERROR_TICKET.getMsg());
            return result;
        }
        CheckRecord checkRecord = null;
        if (!paper.getPassed()) {
            checkRecord = checkRecordServiceImpl.findLastCheckRecordByTicketId(paper.getTicketId());
        } else {
            checkRecord = new CheckRecord();
            checkRecord.setTicketId(paper.getTicketId());
            checkRecord.setCheckCode(paper.getCheckCode());
            checkRecord.setProgramId(paper.getProgramId());
            checkRecord.setProgramName(paper.getProgramName());
            checkRecord.setEventId(paper.getEventId());
            checkRecord.setEventName(paper.getEventName());
            checkRecord.setVenueName(paper.getVenueName());
            checkRecord.setZoneName(paper.getZoneName());
            checkRecord.setSeatName(paper.getSeatName());
            checkRecord.setTicketPrice(new Money(paper.getTicketPrice()).getCent());
            checkRecord.setTicketPriceDesc(paper.getTicketPriceDesc());
            checkRecord.setMobileNo(paper.getMobileNo());
            checkRecord.setCustomerName(paper.getCustomerName());
            checkRecord.setChannel(param.getCheckChannel());
            checkRecord.setCheckMethod(param.getCheckMethod());
            checkRecord.setCheckUserId(paper.getCheckUserId());
            checkRecord.setCheckUserName(paper.getCheckUserName());
            checkRecord.setCheckEntry(paper.getCheckEntry());
            CheckInDeviceParam deviceParam = param.getCheckInDeviceParam();
            if (deviceParam != null) {
                checkRecord.setIdCard(deviceParam.getIdCard());
                checkRecord.setIdCardName(deviceParam.getIdCardName());
                checkRecord.setBindingIdCard(deviceParam.getBindingIdCard());
                checkRecord.setBindingIdCardName(deviceParam.getBindingIdCardName());
                checkRecord.setDeviceType(deviceParam.getDeviceType());
                checkRecord.setDeviceVersion(deviceParam.getDeviceVersion());
                checkRecord.setDeviceUniqueCode(deviceParam.getDeviceUniqueCode());
            }
            checkRecord.setCheckTime(DateUtils.now());
            checkRecord.setEnterpriseId(paper.getEnterpriseId());
            checkRecordServiceImpl.save(checkRecord);
            checkVerifierService.asyncConsumeOrderTicketByCheckRecord(checkRecord);
        }
        TicketPaperDTO dto = checkPaperConverter.convert(checkRecord);
        dto.setPassed(paper.getPassed());
        dto.setPassMessage(paper.getPassMessage());
        result.setSuccess(true);
        result.setTicketPapers(Lists.newArrayList(dto));
        return result;
    }

    @Override
    public CheckRecordQueryDTO findCheckRecordByQuery(CheckInQueryParam param, Pagination pagination) {
        CheckRecordQueryDTO queryResult = new CheckRecordQueryDTO();
        if (param.getCheckDate() != null) {
            queryResult.setCheckDate(param.getCheckDate());
        }
        List<CheckRecord> records = checkRecordServiceImpl.findCheckRecordByQuery(param, pagination);
        if (CollectionUtils.isEmpty(records)) {
            queryResult.setCheckCount(0);
            queryResult.setTicketPapers(Collections.emptyList());
        } else {
            queryResult.setCheckCount(CollectionUtils.size(records));
            queryResult.setTicketPapers(checkPaperConverter.batchConvert(records));
        }
        return queryResult;
    }
}
