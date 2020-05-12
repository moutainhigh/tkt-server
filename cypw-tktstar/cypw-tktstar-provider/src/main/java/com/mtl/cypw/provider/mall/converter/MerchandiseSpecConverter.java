package com.mtl.cypw.provider.mall.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.domain.mall.dto.MerchandiseSpecDTO;
import com.mtl.cypw.mpm.model.ResourceMediaFile;
import com.mtl.cypw.mpm.service.ResourceMediaFileService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.stock.model.Stock;
import com.mtl.cypw.stock.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 16:17
 */
@Slf4j
@Component
public class MerchandiseSpecConverter extends DataConverter<EventPrice, MerchandiseSpecDTO> {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ResourceMediaFileService resourceMediaFileServiceImpl;

    @Override
    public MerchandiseSpecDTO convert(EventPrice eventPrice) {
        if (eventPrice == null) {
            return null;
        }
        MerchandiseSpecDTO dto = new MerchandiseSpecDTO();
        dto.setPriceId(eventPrice.getPriceId());
        dto.setMerchandiseId(eventPrice.getEventId());
        dto.setPriceOrigin(new BigDecimal(eventPrice.getPriceOrigin()));
        dto.setPriceValue(eventPrice.getPriceValue());
        dto.setPriceClass(eventPrice.getPriceClass());
        dto.setPriceTitle(eventPrice.getPriceTitle());
        // 实时查询存库，后续改为异步更新
        Stock stock = stockRepository.findOneBySkuIdAndType(SkuTypeEnum.GOODS, eventPrice.getPriceId());
        if (stock != null) {
            dto.setStockQty(stock.getActualInventory());
            dto.setSoldQty(stock.getSellingStock());
            dto.setTotalQty(stock.getTotalStock());
        } else {
            dto.setStockQty(0);
            dto.setSoldQty(0);
            dto.setTotalQty(0);
        }
        dto.setIsEnable(eventPrice.getIsEnable());
        dto.setMinQty(eventPrice.getMinQty());
        List<ResourceMediaFile> mediaFiles = resourceMediaFileServiceImpl.findFilesByPriceIdWithMall(eventPrice.getPriceId());
        if (CollectionUtils.isNotEmpty(mediaFiles)) {
            dto.setImageSrc(mediaFiles.get(0).getFileSrc());
        }
        return dto;
    }
}
