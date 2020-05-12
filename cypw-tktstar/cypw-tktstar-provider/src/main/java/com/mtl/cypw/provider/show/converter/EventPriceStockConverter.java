package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.EventPriceStockDTO;
import com.mtl.cypw.show.pojo.EventPriceStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Component
public class EventPriceStockConverter {

    public EventPriceStockDTO toDto(EventPriceStock pojo) {
        if (pojo == null) {
            return null;
        }
        EventPriceStockDTO dto = new EventPriceStockDTO();
        dto.setId(pojo.getId());
        dto.setPriceId(pojo.getPriceId());
        dto.setStockQty(pojo.getStockQty());
        dto.setAddQty(pojo.getAddQty());
        dto.setAddDate(pojo.getAddDate());
        dto.setAddUser(pojo.getAddUser());
        return dto;
    }

    public List<EventPriceStockDTO> toDto(List<EventPriceStock> list) {
        if (list == null) {
            return null;
        }
        List<EventPriceStockDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
