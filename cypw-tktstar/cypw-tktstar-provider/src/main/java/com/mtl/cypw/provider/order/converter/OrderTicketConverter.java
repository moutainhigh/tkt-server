package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.dto.OrderTicketDTO;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import com.mtl.cypw.order.model.OrderTicket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderTicketConverter extends DataConverter<OrderTicket, OrderTicketDTO> {

    @Override
    public OrderTicketDTO convert(OrderTicket object) {
        OrderTicketDTO dto = new OrderTicketDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setOrderTicketId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setOrderItemId(object.getOrderItemId());
        dto.setCodeSource(CodeSourceEnum.getObject(object.getCodeSource()));
        dto.setPriceId(object.getPriceId());
        dto.setOriginPrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setTicketPrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setAveragePrice(MoneyUtils.getMoneyByCent(object.getOriginPrice()));
        dto.setCheckChannel(object.getCheckChannel());
        dto.setCheckCode(object.getCheckCode());
        dto.setQrCode(object.getQrCode());
        dto.setCheckStatus(BooleanUtils.toBooleanObject(object.getPrintStatus()));
        dto.setCheckTime(object.getCheckTime());
        dto.setPackageNumber(object.getPackageNumber());
        dto.setChipTid(object.getChipTid());
        dto.setOrderIdentificationId(object.getOrderIdentificationId());
        dto.setPrintStatus(object.getPrintStatus());
        dto.setRepeatPrintCount(object.getRepeatPrintCount());
        dto.setSeatId(object.getSeatId());
        dto.setSeatName(object.getSeatName());
        dto.setTicketDesc(object.getTicketDesc());
        dto.setZoneId(object.getZoneId());
        dto.setZoneName(object.getZoneName());
        dto.setFetchStatus(object.getFetchStatus());
        dto.setFetchedTime(object.getFetchedTime());
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;
    }

}
