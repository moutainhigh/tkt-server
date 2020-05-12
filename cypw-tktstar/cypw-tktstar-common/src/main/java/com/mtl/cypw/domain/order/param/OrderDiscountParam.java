package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 系统下单优惠详情
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDiscountParam extends BaseParam {

    /**
     * 优惠类型（0-线下优惠, 1-折扣券, 2-现金券, 3-兑换券）
     */
    private PromotionTypeEnum couponType;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 优惠折扣
     */
    private BigDecimal discountRate;
    /**
     * 优惠说明
     */
    private String discountNote;

    @Override
    public void checkParam() {

    }
}
