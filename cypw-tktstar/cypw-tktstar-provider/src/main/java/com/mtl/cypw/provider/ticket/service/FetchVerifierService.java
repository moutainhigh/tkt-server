package com.mtl.cypw.provider.ticket.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.ticket.dto.OrderPaperDTO;
import com.mtl.cypw.domain.ticket.enums.FetchMethodEnum;
import com.mtl.cypw.domain.ticket.enums.FetchStatusEnum;
import com.mtl.cypw.domain.ticket.enums.FetchVerifierEnum;
import com.mtl.cypw.domain.ticket.param.FetchCommand;
import com.mtl.cypw.member.pojo.CheckInUser;
import com.mtl.cypw.member.service.CheckInUserService;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderTicket;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.order.service.OrderTicketService;
import com.mtl.cypw.provider.ticket.converter.ItemPaperConverter;
import com.mtl.cypw.provider.ticket.converter.TicketPaperConverter;
import com.mtl.cypw.ticket.exception.fetch.FetchCodeNotFoundException;
import com.mtl.cypw.ticket.exception.fetch.FetchDataFailedException;
import com.mtl.cypw.ticket.exception.fetch.FetchMethodNotSupportedException;
import com.mtl.cypw.ticket.exception.fetch.FetchPermissionDeniedException;
import com.mtl.cypw.ticket.exception.fetch.FetchRepeatedException;
import com.mtl.cypw.ticket.model.CheckRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 20:11
 */
@Slf4j
@Component
public class FetchVerifierService {

    @Autowired
    private CheckInUserService checkInUserService;

    @Autowired
    private OrderTicketService orderTicketServiceImpl;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Autowired
    private ItemPaperConverter itemPaperConverter;

    @Autowired
    private TicketPaperConverter ticketPaperConverter;

    public OrderPaperDTO verificationByCode(FetchCommand command) {
        CheckInUser checkInUser = null;
        if (FetchMethodEnum.APPLET.getCode() == command.getFetchMethod()) {
            checkInUser = checkInUserService.getCheckInUser(command.getFetchUserId());
            if (checkInUser == null || !checkInUser.isValid()) {
                throw new FetchPermissionDeniedException(ErrorCode.ERROR_TICKET_FETCH_PERMISSION_DENIED.getMsg());
            }
        }
        Order order = orderQueryServiceImpl.findOneByFetchCode(command.getFetchCode(), command.getEnterpriseId());
        this.unifiedVerifier(order);
        OrderPaperDTO paper = this.packaging(order, checkInUser);
        return paper;
    }

    public void unifiedVerifier(Order order) {
        if (order == null || order.getDelivery() == null || CollectionUtils.isEmpty(order.getOrderTickets())) {
            throw new FetchCodeNotFoundException(ErrorCode.ERROR_TICKET_FETCH_CODE_FAILED.getMsg());
        }
        if (OrderStatusEnum.REFUNDED.getCode() == order.getOrderStatus() || OrderStatusEnum.REFUNDING.getCode() == order.getOrderStatus()) {
            throw new FetchRepeatedException(ErrorCode.ERROR_TICKET_FETCH_HAVE_BEEN_REFUND.getMsg());
        }
        if (FetchStatusEnum.FETCHED.getCode() == order.getDelivery().getFetchStatus()) {
            throw new FetchRepeatedException(
                    OrderTypeEnum.ONLY_GOODS.getCode() == order.getOrderType()
                            ? ErrorCode.ERROR_TICKET_FETCH_GOODS_REPEATED.getMsg()
                            : ErrorCode.ERROR_TICKET_FETCH_TICKET_REPEATED.getMsg());
        }
        if (FetchStatusEnum.FETCHING.getCode() == order.getDelivery().getFetchStatus()) {
            throw new FetchDataFailedException(ErrorCode.ERROR_TICKET_FETCH_DATA_FAILED.getMsg());
        }
        if (DeliverTypeEnum.EXPRESS.getCode() == order.getDeliverType() || DeliverTypeEnum.E_TICKET.getCode() == order.getDeliverType()) {
            throw new FetchMethodNotSupportedException(ErrorCode.ERROR_TICKET_FETCH_DATA_FAILED.getMsg());
        }
        List<OrderTicket> orderTickets = order.getOrderTickets().stream().filter(
                ticket -> ticket.getFetchStatus() == FetchStatusEnum.NOT_FETCHED.getCode()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(orderTickets)) {
            throw new FetchDataFailedException(ErrorCode.ERROR_TICKET_FETCH_DATA_FAILED.getMsg());
        }
        order.setOrderTickets(orderTickets);
    }

    public OrderPaperDTO packaging(Order order, CheckInUser checkInUser) {
        OrderPaperDTO paper = new OrderPaperDTO();
        paper.setPassed(true);
        paper.setPassMessage(FetchVerifierEnum.PASSED.getDesc());
        paper.setOrderId(order.getId());
        paper.setOrderNo(order.getOrderNo());

        paper.setOrderTitle(order.getOrderTitle());
        paper.setMobileNo(order.getMobileNo());
        paper.setOrderType(OrderTypeEnum.getObject(order.getOrderType()));
        paper.setOrderStatus(OrderStatusEnum.getObject(order.getOrderStatus()));
        paper.setChannel(ChannelEnum.getObject(order.getChannelId()));

        paper.setDeliverType(DeliverTypeEnum.getObject(order.getDeliverType()));
        paper.setPayType(PayTypeEnum.getObject(order.getPaymentTypeId()));
        paper.setPaidTime(order.getPaidTime());

        paper.setDiscountFee(MoneyUtils.getMoneyByCent(order.getDiscountFee()));
        paper.setOriginAmount(MoneyUtils.getMoneyByCent(order.getOriginAmount()));
        paper.setActualAmount(MoneyUtils.getMoneyByCent(order.getActualAmount()));
        paper.setTicketAmount(MoneyUtils.getMoneyByCent(order.getTicketAmount()));

        paper.setFetchCode(order.getFetchCode());
        paper.setOrderTime(order.getOrderTime());
        paper.setEnterpriseId(order.getEnterpriseId());

        if (checkInUser != null) {
            paper.setFetchUserId(checkInUser.getId());
            paper.setFetchUserName(checkInUser.getPersonName());
        }
        if (order.isGoods()) {
            paper.setItemPapers(itemPaperConverter.batchConvert(order.getOrderItems()));
        }
        paper.setTicketPapers(ticketPaperConverter.batchConvert(order.getOrderTickets()));
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
