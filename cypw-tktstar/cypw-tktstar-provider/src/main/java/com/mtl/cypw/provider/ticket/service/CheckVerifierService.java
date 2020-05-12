package com.mtl.cypw.provider.ticket.service;

import com.google.common.collect.Lists;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.show.enums.CheckInTypeEnum;
import com.mtl.cypw.domain.show.enums.CheckTimeTypeEnum;
import com.mtl.cypw.domain.ticket.dto.TicketPaperDTO;
import com.mtl.cypw.domain.ticket.enums.CheckStatusEnum;
import com.mtl.cypw.domain.ticket.enums.TicketVerifierEnum;
import com.mtl.cypw.member.pojo.CheckInUser;
import com.mtl.cypw.member.service.CheckInUserService;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderTicket;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.order.service.OrderTicketService;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.pojo.ProgramCheckIn;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.ProgramService;
import com.mtl.cypw.ticket.exception.check.CheckCodeNotFoundException;
import com.mtl.cypw.ticket.exception.check.CheckDataNotInEffectException;
import com.mtl.cypw.ticket.exception.check.CheckMethodNotSupportedException;
import com.mtl.cypw.ticket.exception.check.CheckPermissionDeniedException;
import com.mtl.cypw.ticket.exception.check.CheckShowClosedException;
import com.mtl.cypw.ticket.model.CheckRecord;
import com.mtl.cypw.ticket.service.CheckRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 20:11
 */
@Slf4j
@Component
public class CheckVerifierService {

    @Autowired
    private CheckInUserService checkInUserService;

    @Autowired
    private CheckRecordService checkRecordServiceImpl;

    @Autowired
    private OrderTicketService orderTicketServiceImpl;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Autowired
    private EventService eventService;

    @Autowired
    private ProgramService programService;

    public TicketPaperDTO verificationByCode(String checkCode, Integer checkUserId) {
        CheckInUser checkInUser = checkInUserService.getCheckInUser(checkUserId);
        if (checkInUser == null || !checkInUser.isValid()) {
            throw new CheckPermissionDeniedException(ErrorCode.ERROR_TICKET_CHECK_PERMISSION_DENIED.getMsg());
        }
        OrderTicket ticket = orderTicketServiceImpl.findOneByCheckCode(checkCode, checkInUser.getEnterpriseId());
        if (ticket == null) {
            throw new CheckCodeNotFoundException(ErrorCode.ERROR_TICKET_CHECK_CODE_FAILED.getMsg());
        }
        Order order = orderQueryServiceImpl.findOneById(ticket.getOrderId());
        if (DeliverTypeEnum.E_TICKET.getCode() != order.getDeliverType()) {
            throw new CheckMethodNotSupportedException(ErrorCode.ERROR_TICKET_CHECK_NOT_SUPPORTED.getMsg());
        }
        TicketPaperDTO paper = packaging(ticket, checkInUser, order);
        this.unifiedVerifier(ticket, checkInUser, order, paper);
        return paper;
    }

    public List<TicketPaperDTO> verificationByMobileNo(String mobileNo, Integer checkUserId) {
        CheckInUser checkInUser = checkInUserService.getCheckInUser(checkUserId);
        if (checkInUser == null || !checkInUser.isValid()) {
            throw new CheckPermissionDeniedException(ErrorCode.ERROR_TICKET_CHECK_PERMISSION_DENIED.getMsg());
        }
        List<OrderTicket> tickets = orderTicketServiceImpl.findETicketsByMobileNo(mobileNo, checkInUser.getEnterpriseId());
        if (CollectionUtils.isEmpty(tickets)) {
            throw new CheckCodeNotFoundException(ErrorCode.ERROR_TICKET_CHECK_MOBILE_FAILED.getMsg());
        }
        List<TicketPaperDTO> paperList = Lists.newArrayList();
        for (OrderTicket ticket : tickets) {
            Order order = orderQueryServiceImpl.findOneById(ticket.getOrderId());
            paperList.add(packaging(ticket, checkInUser, order));
        }
        return paperList;
    }

    public void unifiedVerifier(OrderTicket ticket, CheckInUser user, Order order, TicketPaperDTO paper) {
        Program program = programService.getProgramById(order.getProductId());
        Event event = eventService.findOneById(order.getEventId());
        if (CommonStateEnum.VALID.getCode() != event.getIsEnable()) {
            throw new CheckShowClosedException(ErrorCode.ERROR_TICKET_CHECK_DATA_FAILED.getMsg());
        }
        Date current = DateUtils.now();
        if (CheckTimeTypeEnum.FIXED_TIME.getCode() == program.getCheckinTimeType()) {
            if (!event.canCheckIn4Normal(current) || !event.canCheckIn4Pass(current)) {
                throw new CheckDataNotInEffectException(ErrorCode.ERROR_TICKET_CHECK_DATA_FAILED.getMsg());
            }
        }
        int count = checkRecordServiceImpl.findCheckCountByTicketId(ticket.getId());
        if (CheckInTypeEnum.BY_TIMES.getCode() == program.getCheckinCountType()
                && count >= program.getCheckinCount()) {
            paper.setPassed(false);
            paper.setPassMessage(TicketVerifierEnum.SPENT.getDesc());
        } else if (CheckInTypeEnum.BY_ENTRANCE.getCode() == program.getCheckinCountType()) {
            ProgramCheckIn config = programService.getCheckInConfigByCheckInUserId(program.getProgramId(), user.getId());
            if (config == null) {
                throw new CheckPermissionDeniedException(ErrorCode.ERROR_TICKET_CHECK_PERMISSION_DENIED.getMsg());
            }
            int entryCount = checkRecordServiceImpl.findCheckCountByTicketIdAndCheckEntryId(ticket.getId(), config.getId());
            if (entryCount >= entryCount) {
                paper.setPassed(false);
                paper.setPassMessage(TicketVerifierEnum.SPENT.getDesc());
            }
        }
    }

    public TicketPaperDTO packaging(OrderTicket ticket, CheckInUser user, Order order) {
        TicketPaperDTO paper = new TicketPaperDTO();
        if (CheckStatusEnum.COMPLETED.getCode() == ticket.getCheckStatus()) {
            paper.setPassed(false);
            paper.setPassMessage(TicketVerifierEnum.SPENT.getDesc());
        } else {
            paper.setPassed(true);
            paper.setPassMessage(TicketVerifierEnum.PASSED.getDesc());
        }
        paper.setTicketId(ticket.getId());
        paper.setCheckCode(ticket.getCheckCode());
        paper.setProgramId(order.getProductId());
        paper.setProgramName(order.getProductName());
        paper.setEventId(order.getEventId());
        paper.setEventName(order.getOrderSnapshot().getShowName());
        paper.setVenueName(order.getVenueName());
        paper.setZoneName(ticket.getZoneName());
        paper.setSeatName(ticket.getSeatName());
        paper.setTicketPrice(MoneyUtils.getMoneyByCent(ticket.getOriginPrice()).getAmount());
        paper.setTicketPriceDesc(ticket.getTicketDesc());
        paper.setMobileNo(order.getMobileNo());
        paper.setCustomerName(order.getDelivery().getAddresseeName());
        paper.setCheckUserId(user.getId());
        paper.setCheckUserName(user.getPersonName());
        paper.setCheckEntry("");
        paper.setEnterpriseId(user.getEnterpriseId());
        return paper;
    }

    public boolean asyncConsumeOrderTicketByCheckRecord(CheckRecord record) {
        if (record == null || record.brandNew()) {
            return false;
        }
        ThreadPoolUtil.execute(() -> {
            log.info("consume order ticket, ticketId [{}], datetime [{}]", record.getTicketId(),
                    DateUtils.format(DateUtils.now(), DateUtils.millisecondFormat));
            boolean hasCompleted = orderTicketServiceImpl.verifyOrderCompleted(record.getTicketId());
            if (!hasCompleted) {
                orderTicketServiceImpl.checking(record.getTicketId(), record.getChannel(), record.getCheckTime());
            } else {
                orderTicketServiceImpl.checkCompleted(record.getTicketId(), record.getChannel(), record.getCheckTime());
            }
        });
        return true;
    }

}
