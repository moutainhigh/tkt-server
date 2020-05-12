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
import com.mtl.cypw.order.exception.init.InvalidCouponCodeException;
import com.mtl.cypw.order.exception.init.OrderAmountConsistentException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.provider.order.service.uniform.SpecialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 14:20
 */
@Slf4j
@Component("cashSpecialService")
public class CashSpecialServiceImpl implements SpecialService {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionCouponService promotionCouponService;

    @Override
    public OrderDiscountDetail buildDiscountDetail(Order order, OrderCouponParam couponParam) {
        Assert.notNull(order, "order is null");
        Assert.notNull(couponParam, "couponParam is null");
        Assert.notNull(couponParam.getCouponId(), "couponId is null");
        Assert.isTrue(PromotionTypeEnum.CASH_COUPON.getCode() == couponParam.getCouponType().getCode(), "couponType error");
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
        long orderAmountRestriction = new Money(promotion.getOrderAmountRestriction()).getCent();
        if (orderAmountRestriction > 0 && order.getOriginAmount() < orderAmountRestriction) {
            log.error("订单金额还不足以参加优惠哦, orderAmount {}, orderAmountRestriction {}",
                    order.getOriginAmount(), orderAmountRestriction);
            throw new OrderAmountConsistentException("订单金额还不足以参加优惠哦");
        }
        int orderQtyRestriction = promotion.getMinQtyRestriction();
        if (orderAmountRestriction > 0 && order.getSkuQuantity() < orderQtyRestriction) {
            log.error("商品数量还不足以参加优惠哦, skuQuantity {}, orderQtyRestriction {}",
                    order.getSkuQuantity(), orderQtyRestriction);
            throw new OrderAmountConsistentException("商品数量还不足以参加优惠哦");
        }
        OrderDiscountDetail discountDetail = new OrderDiscountDetail();
        discountDetail.setCouponCode(coupon.getCouponCode());
        discountDetail.setCouponId(coupon.getCouponId());
        discountDetail.setDiscountType(PromotionTypeEnum.CASH_COUPON.getCode());
        discountDetail.setStatus(CommonStateEnum.VALID.getCode());
        discountDetail.setEnterpriseId(order.getEnterpriseId());
        long discountAmount = new Money(promotion.getPromotionAmount()).getCent();
        if (discountAmount >= order.getTicketAmount()) {
            discountDetail.setDiscountAmount(order.getTicketAmount());
        } else {
            discountDetail.setDiscountAmount(discountAmount);
        }
        return discountDetail;
    }
}
