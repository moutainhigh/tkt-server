package com.mtl.cypw.provider.ticket.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.ticket.dto.TicketPaperDTO;
import com.mtl.cypw.ticket.model.CheckRecord;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 17:46
 */
@Component
public class CheckPaperConverter extends DataConverter<CheckRecord, TicketPaperDTO> {

    @Override
    public TicketPaperDTO convert(CheckRecord input) {
        if (input == null) {
            return null;
        }
        TicketPaperDTO dto = new TicketPaperDTO();
        dto.setTicketId(input.getTicketId());
        dto.setProgramId(input.getProgramId());
        dto.setProgramName(input.getProgramName());
        dto.setEventId(input.getEventId());
        dto.setEventName(input.getEventName());
        dto.setChannel(input.getChannel());
        dto.setDeviceType(input.getDeviceType());
        dto.setDeviceVersion(input.getDeviceVersion());
        dto.setDeviceUniqueCode(input.getDeviceUniqueCode());
        dto.setCheckMethod(input.getCheckMethod());
        dto.setIdCard(input.getIdCard());
        dto.setIdCardName(input.getIdCardName());
        dto.setBindingIdCard(input.getBindingIdCard());
        dto.setBindingIdCardName(input.getBindingIdCardName());
        dto.setCheckTime(input.getCheckTime());
        dto.setCheckCode(input.getCheckCode());
        dto.setCheckUserId(input.getCheckUserId());
        dto.setCheckUserName(input.getCheckUserName());
        dto.setCheckEntry(input.getCheckEntry());
        dto.setTicketPrice(MoneyUtils.getMoneyByCent(input.getTicketPrice()).getAmount());
        dto.setVenueName(input.getVenueName());
        dto.setZoneName(input.getZoneName());
        dto.setSeatName(input.getSeatName());
        dto.setMobileNo(input.getMobileNo());
        dto.setCustomerName(input.getCustomerName());
        dto.setEnterpriseId(input.getEnterpriseId());
        return dto;
    }

}
