package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.enums.PriceColorEnum;
import com.mtl.cypw.show.pojo.EventPrice;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Component
public class EventPriceConverter {

    public EventPriceDTO toDto(EventPrice eventPrice) {
        if (eventPrice == null) {
            return null;
        }
        EventPriceDTO dto = new EventPriceDTO();
        dto.setPriceId(eventPrice.getPriceId());
        dto.setEventId(eventPrice.getEventId());
        dto.setPriceOrigin(eventPrice.getPriceOrigin());
        dto.setPriceValue(eventPrice.getPriceValue());
        dto.setPriceClass(eventPrice.getPriceClass());
        dto.setTotalQty(eventPrice.getTotalQty());
        dto.setPriceTitle(eventPrice.getPriceTitle());
        dto.setStockQty(eventPrice.getStockQty());
        dto.setSoldQty(eventPrice.getSoldQty());
        dto.setIsEnable(eventPrice.getIsEnable());
        dto.setAddDate(eventPrice.getAddDate());
        dto.setUpdateDate(eventPrice.getUpdateDate());
        dto.setAddUser(eventPrice.getAddUser());
        dto.setUpdateUser(eventPrice.getUpdateUser());
        if (StringUtils.isNotBlank(eventPrice.getPriceClass())) {
            dto.setPriceColor(PriceColorEnum.getObject(eventPrice.getPriceClass()).getColor());
        }
        dto.setMinQty(eventPrice.getMinQty());
        return dto;
    }

    public List<EventPriceDTO> toDto(List<EventPrice> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<EventPriceDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
