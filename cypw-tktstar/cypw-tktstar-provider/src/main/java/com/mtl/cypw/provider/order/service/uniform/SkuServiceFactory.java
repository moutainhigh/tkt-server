package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.common.enums.SkuTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:45
 */
@Component
public class SkuServiceFactory {

    @Resource(name = "ticketSkuService")
    private SkuService ticketSkuService;

    @Resource(name = "goodsSkuService")
    private SkuService goodsSkuService;


    public SkuService selector(SkuTypeEnum skuType) {
        if (SkuTypeEnum.TICKET.getCode() == skuType.getCode()) {
            return ticketSkuService;
        } else if (SkuTypeEnum.GOODS.getCode() == skuType.getCode()) {
            return goodsSkuService;
        }
        return ticketSkuService;
    }
}
