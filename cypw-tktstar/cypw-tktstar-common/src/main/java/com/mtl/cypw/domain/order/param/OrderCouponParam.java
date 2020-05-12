package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCouponParam extends BaseParam {

    /**
     * 优惠活动ID
     */
    private Integer promotionId;
    /**
     * 优惠券ID（抵现券/折扣券/兑换券）
     */
    private Integer couponId;
    /**
     * 优惠类型（1-折扣券, 2-现金券, 3-兑换券）
     */
    private PromotionTypeEnum couponType;
    /**
     * sku id
     */
    private Integer skuId;

    @Override
    public void checkParam() {

    }
}
