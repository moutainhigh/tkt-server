package com.mtl.cypw.provider.mall.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.domain.mall.dto.MerchandiseDTO;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 16:17
 */
@Slf4j
@Component
public class MerchandiseConverter extends DataConverter<Merchandise, MerchandiseDTO> {

    @Autowired
    private EventPriceService eventPriceService;

    @Override
    public MerchandiseDTO convert(Merchandise object) {
        if (object == null) {
            return null;
        }
        MerchandiseDTO dto = new MerchandiseDTO();
        dto.setMerchandiseId(object.getMerchandiseId());
        dto.setMerchandiseName(object.getMerchandiseName());
        dto.setMerchandiseCode(object.getMerchandiseCode());
        dto.setMerchandiseBrief(object.getMerchandiseBrief());
        dto.setMerchandiseContent(object.getMerchandiseContent());
        dto.setMerchandiseLimitCnt(object.getMerchandiseLimitCnt());
        EventPrice price = eventPriceService.searchMinEventPriceByEventId(object.getMerchandiseId());
        if (price != null) {
            dto.setBottomPrice(price.getPriceValue());
        } else {
            dto.setBottomPrice(object.getMerchandisePrice());
        }
        dto.setSortOrder(object.getSortOrder());
        dto.setBeginDate(object.getBeginDate());
        dto.setEndDate(object.getEndDate());
        dto.setIsEnable(object.getIsEnable());
        dto.setSaleable(object.isSaleable());
        dto.setMerchandiseImage(object.getMerchandiseImage());
        return dto;
    }

    public MerchandiseDTO convertDetail(Merchandise object) {
        if (object == null) {
            return null;
        }
        MerchandiseDTO dto = this.convert(object);
        dto.setMerchandiseContent(object.getMerchandiseContent());
        dto.setPurchaseNotice(object.getPurchaseNotice());
        return dto;
    }
}
