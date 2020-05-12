package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 14:09
 */
@Component
public class SpecialServiceFactory {

    @Resource(name = "cashSpecialService")
    private SpecialService cashSpecialService;

    @Resource(name = "discountSpecialService")
    private SpecialService discountSpecialService;

    @Resource(name = "exchangeSpecialService")
    private SpecialService exchangeSpecialService;

    public SpecialService selector(PromotionTypeEnum typeEnum) {
        if (PromotionTypeEnum.DISCOUNT_COUPON.getCode() == typeEnum.getCode()) {
            return discountSpecialService;
        } else if (PromotionTypeEnum.EXCHANGE_COUPON.getCode() == typeEnum.getCode()) {
            return exchangeSpecialService;
        }
        return cashSpecialService;
    }
}
