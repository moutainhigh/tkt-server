package com.mtl.cypw.domain.coupon.param;

import com.mtl.cypw.domain.coupon.enums.CouponStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/6.
 */
@Data
public class PromotionCouponQueryParam {

    /**
     * 是否使用
     */
    private Integer isUsed;

    /**
     * 是否绑定
     */
    private Integer isBind;

    private Integer memberId;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 优惠活动ID
     */
    private List<Integer> promotionIds;

    /**
     * 优惠券状态
     */
    private CouponStateEnum couponStateEnum;

    /**
     * 优惠券过期时间，筛选
     */
    private Date endEndDate;
}
