package com.mtl.cypw.provider.ticket.converter;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.juqitech.converter.DataConverter;
import com.mtl.cypw.domain.ticket.dto.VoucherPaperDTO;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderTicket;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.ticket.model.FetchVoucher;
import com.mtl.cypw.ticket.service.FetchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 17:46
 */
@Component
public class VoucherPaperConverter extends DataConverter<FetchVoucher, VoucherPaperDTO> {

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Autowired
    private ItemPaperConverter itemPaperConverter;

    @Autowired
    private TicketPaperConverter ticketPaperConverter;

    @Autowired
    private FetchVoucherService fetchVoucherServiceImpl;

    @Override
    public VoucherPaperDTO convert(FetchVoucher input) {
        if (input == null) {
            return null;
        }
        VoucherPaperDTO dto = new VoucherPaperDTO();
        dto.setId(String.valueOf(input.getId()));
        dto.setVoucherId(input.getId());
        dto.setOrderId(input.getOrderId());
        Order order = orderQueryServiceImpl.findOneById(input.getOrderId());
        dto.setOrderNo(order.getOrderNo());
        dto.setVoucherNo(input.getVoucherNo());
        dto.setVoucherType(input.getVoucherType());
        dto.setVoucherDesc(input.getVoucherDesc());
        dto.setHandleType(input.getHandleType());
        dto.setFetchMethod(input.getFetchMethod());
        dto.setMachineId(input.getMachineId());
        dto.setMacId(input.getMacId());
        dto.setIdCard(input.getIdCard());
        dto.setIdCardName(input.getIdCardName());
        dto.setFetchLockTime(input.getFetchLockTime());
        dto.setFetchCompleteTime(input.getFetchCompleteTime());
        dto.setFetchCode(input.getFetchCode());
        dto.setFetchUserId(input.getFetchUserId());
        dto.setFetchUserName(input.getFetchUserName());
        dto.setQuantity(input.getQuantity());
        dto.setStatus(input.getStatus());
        dto.setEnterpriseId(input.getEnterpriseId());
        dto.setItemPapers(itemPaperConverter.batchConvert(order.getOrderItems()));
        List<Integer> ticketIds = fetchVoucherServiceImpl.findTicketIdListByVoucherId(input.getId());
        Collection<OrderTicket> tickets = Collections2.filter(order.getOrderTickets(), ticket -> ticketIds.contains(ticket.getId()));
        dto.setTicketPapers(ticketPaperConverter.batchConvert(Lists.newArrayList(tickets)));
        return dto;
    }

}
