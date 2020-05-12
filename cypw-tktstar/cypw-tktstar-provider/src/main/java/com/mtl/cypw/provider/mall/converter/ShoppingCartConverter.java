package com.mtl.cypw.provider.mall.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.domain.mall.dto.ShoppingCartDTO;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.mall.model.ShoppingCart;
import com.mtl.cypw.mall.service.MerchandiseService;
import com.mtl.cypw.mpm.model.ResourceMediaFile;
import com.mtl.cypw.mpm.service.ResourceMediaFileService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
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
public class ShoppingCartConverter extends DataConverter<ShoppingCart, ShoppingCartDTO> {

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private ResourceMediaFileService resourceMediaFileServiceImpl;

    @Override
    public ShoppingCartDTO convert(ShoppingCart object) {
        ShoppingCartDTO dto = new ShoppingCartDTO();
        dto.setShoppingCartId(object.getId());
        dto.setMemberId(object.getMemberId());
        dto.setProductId(object.getProductId());
        Merchandise merchandise = merchandiseService.get(object.getProductId());
        dto.setProductName(merchandise.getMerchandiseName());
        dto.setQuantity(object.getQuantity());
        dto.setLimitCnt(merchandise.getMerchandiseLimitCnt());
        dto.setSkuId(object.getSkuId());
        dto.setSkuType(object.getSkuType());
        EventPrice eventPrice = eventPriceService.getEventPriceById(object.getSkuId());
        if (!merchandise.isSaleable() || !eventPrice.isEnabled()) {
            dto.setStatus(CommonStateEnum.INVALID.getCode());
        } else {
            dto.setStatus(object.getStatus());
        }
        List<ResourceMediaFile> mediaFiles = resourceMediaFileServiceImpl.findFilesByPriceIdWithMall(eventPrice.getPriceId());
        if (CollectionUtils.isNotEmpty(mediaFiles)) {
            dto.setProductImgSrc(mediaFiles.get(0).getFileSrc());
        } else  {
            dto.setProductImgSrc(merchandise.getMerchandiseImage());
        }
        dto.setSkuName(eventPrice.getPriceTitle());
        dto.setUnitPrice(eventPrice.getPriceValue());
        dto.setOriginalPrice(new BigDecimal(eventPrice.getPriceOrigin()));
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;

    }
}
