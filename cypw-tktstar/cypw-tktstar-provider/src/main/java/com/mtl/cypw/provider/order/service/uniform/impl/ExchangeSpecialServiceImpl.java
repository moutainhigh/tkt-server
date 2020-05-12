package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.coupon.pojo.PromotionCoupon;
import com.mtl.cypw.coupon.service.PromotionCouponService;
import com.mtl.cypw.coupon.service.PromotionService;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.domain.order.param.OrderCouponParam;
import com.mtl.cypw.order.exception.init.InvalidExchangeCouponCodeException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.provider.order.service.uniform.SpecialService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 14:20
 */
@Slf4j
@Component("exchangeSpecialService")
public class ExchangeSpecialServiceImpl implements SpecialService {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private PromotionCouponService promotionCouponService;

    @Override
    public OrderDiscountDetail buildDiscountDetail(Order order, OrderCouponParam couponParam) {
        Assert.notNull(order, "order is null");
        Assert.notNull(couponParam, "couponParam is null");
        Assert.notNull(couponParam.getCouponId(), "couponId is null");
        Assert.notNull(couponParam.getSkuId(), "skuId is null");
        Assert.isTrue(PromotionTypeEnum.EXCHANGE_COUPON.getCode() == couponParam.getCouponType().getCode(), "couponType error");
        PromotionCoupon coupon = promotionCouponService.findOneById(couponParam.getCouponId());
        if (coupon == null) {
            log.error("无效的兑换券, param {}", JsonUtils.toJson(couponParam));
            throw new InvalidExchangeCouponCodeException("无效的兑换券");
        }
        if (!coupon.isUnUsed()) {
            log.error("兑换券已被使用, couponCode {}", coupon.getCouponCode());
            throw new InvalidExchangeCouponCodeException("兑换券已被使用");
        }
        if (!coupon.isWholeEnabled()) {
            log.error("兑换券暂不可用, couponCode {}", coupon.getCouponCode());
            throw new InvalidExchangeCouponCodeException("兑换券暂不可用");
        }
        if (!coupon.isOwn(order.getMemberId())) {
            log.error("无效的兑换券, memberId[{}], couponCode[{}]", order.getMemberId(), coupon.getCouponCode());
            throw new InvalidExchangeCouponCodeException("无效的兑换券");
        }
        Promotion promotion = promotionService.getPromotionById(coupon.getPromotionId());
        if (!promotion.isWholeEnabled()) {
            log.error("优惠活动已过期, promotion {}", JsonUtils.toJson(promotion));
            throw new InvalidExchangeCouponCodeException("优惠活动已过期");
        }
        EventPrice price = eventPriceService.getEventPriceById(couponParam.getSkuId());
        if (price == null || !price.isEnabled()) {
            log.error("兑换商品不存在或已失效, skuId {}", couponParam.getSkuId());
            throw new InvalidExchangeCouponCodeException("兑换商品不存在或已失效");
        }
        OrderDiscountDetail discountDetail = new OrderDiscountDetail();
        discountDetail.setCouponCode(coupon.getCouponCode());
        discountDetail.setCouponId(coupon.getCouponId());
        discountDetail.setDiscountType(PromotionTypeEnum.EXCHANGE_COUPON.getCode());
        discountDetail.setStatus(CommonStateEnum.VALID.getCode());
        discountDetail.setEnterpriseId(order.getEnterpriseId());
        discountDetail.setExchangeSkuId(price.getPriceId());
        discountDetail.setDiscountAmount(new Money(price.getPriceValue()).getCent());
        return discountDetail;
    }
}
