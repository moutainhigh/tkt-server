package com.mtl.cypw.domain.coupon.param;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/5.
 */
@Data
public class MemberCouponParam {

    private Integer promotionId;

    private Integer memberId;

    private String couponCode;
}
