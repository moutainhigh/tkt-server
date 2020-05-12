package com.mtl.cypw.provider.ticket.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.ticket.dto.FetchTicketPaperDTO;
import com.mtl.cypw.order.model.OrderTicket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class TicketPaperConverter extends DataConverter<OrderTicket, FetchTicketPaperDTO> {

    @Override
    public FetchTicketPaperDTO convert(OrderTicket object) {
        FetchTicketPaperDTO dto = new FetchTicketPaperDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setTicketId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setOrderItemId(object.getOrderItemId());
        dto.setPriceId(object.getPriceId());
        dto.setOriginPrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setTicketPrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setAveragePrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setCheckCode(object.getCheckCode());
        dto.setQrCode(object.getQrCode());
        dto.setPackageNumber(object.getPackageNumber());
        dto.setChipTid(object.getChipTid());
        dto.setOrderIdentificationId(object.getOrderIdentificationId());
        dto.setPrintStatus(object.getPrintStatus());
        dto.setRepeatPrintCount(object.getRepeatPrintCount());
        dto.setSeatId(object.getSeatId());
        dto.setTicketDesc(object.getTicketDesc());
        dto.setZoneId(object.getZoneId());
        dto.setZoneName(object.getZoneName());
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;
    }

}
