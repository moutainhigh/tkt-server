package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.util.BigDecimalUtils;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.coupon.pojo.PromotionCoupon;
import com.mtl.cypw.coupon.service.PromotionCouponService;
import com.mtl.cypw.coupon.service.PromotionService;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.domain.order.param.OrderCouponParam;
import com.mtl.cypw.order.exception.init.InvalidCouponCodeException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.provider.order.service.uniform.SpecialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 14:20
 */
@Slf4j
@Component("discountSpecialService")
public class DiscountSpecialServiceImpl implements SpecialService {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionCouponService promotionCouponService;

    @Override
    public OrderDiscountDetail buildDiscountDetail(Order order, OrderCouponParam couponParam) {
        Assert.notNull(order, "order is null");
        Assert.notNull(couponParam, "couponParam is null");
        Assert.notNull(couponParam.getCouponId(), "couponId is null");
        Assert.isTrue(PromotionTypeEnum.DISCOUNT_COUPON.getCode() == couponParam.getCouponType().getCode(), "couponType error");
        PromotionCoupon coupon = promotionCouponService.findOneById(couponParam.getCouponId());
        if (coupon == null) {
            log.error("无效的优惠券, param {}", JsonUtils.toJson(couponParam));
            throw new InvalidCouponCodeException("无效的优惠券");
        }
        if (!coupon.isUnUsed()) {
            log.error("优惠券已被使用, couponCode {}", coupon.getCouponCode());
            throw new InvalidCouponCodeException("优惠券已被使用");
        }
        if (!coupon.isWholeEnabled()) {
            log.error("优惠券暂不可用, couponCode {}", coupon.getCouponCode());
            throw new InvalidCouponCodeException("优惠券暂不可用");
        }
        if (!coupon.isOwn(order.getMemberId())) {
            log.error("无效的优惠券, memberId[{}], couponCode[{}]", order.getMemberId(), coupon.getCouponCode());
            throw new InvalidCouponCodeException("无效的优惠券");
        }
        Promotion promotion = promotionService.getPromotionById(coupon.getPromotionId());
        if (!promotion.isWholeEnabled()) {
            log.error("优惠活动已过期, promotion {}", JsonUtils.toJson(promotion));
            throw new InvalidCouponCodeException("优惠活动已过期");
        }
        OrderDiscountDetail discountDetail = new OrderDiscountDetail();
        discountDetail.setCouponCode(coupon.getCouponCode());
        discountDetail.setCouponId(coupon.getCouponId());
        discountDetail.setDiscountType(PromotionTypeEnum.DISCOUNT_COUPON.getCode());
        discountDetail.setStatus(CommonStateEnum.VALID.getCode());
        discountDetail.setEnterpriseId(order.getEnterpriseId());
        BigDecimal orderTicketAmount = BigDecimal.valueOf(order.getTicketAmount());
        BigDecimal afterDiscountAmount = orderTicketAmount.multiply(promotion.getPromotionDiscount());
        discountDetail.setDiscountAmount(BigDecimalUtils.rounded2Long(orderTicketAmount.subtract(afterDiscountAmount)));
        discountDetail.setDiscountNumber(promotion.getPromotionDiscount().multiply(new BigDecimal(100)).intValue());
        return discountDetail;
    }
}
