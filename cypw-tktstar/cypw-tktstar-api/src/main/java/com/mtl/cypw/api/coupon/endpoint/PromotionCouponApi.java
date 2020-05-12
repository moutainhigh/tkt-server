package com.mtl.cypw.api.coupon.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.coupon.dto.PromotionCouponDTO;
import com.mtl.cypw.domain.coupon.param.MemberCouponParam;
import com.mtl.cypw.domain.coupon.param.PromotionCouponQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public interface PromotionCouponApi {

    /**
     * 查询用户所领取的优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/promotion_coupon/promotion_coupons")
    TMultiResult<PromotionCouponDTO> searchPromotionCouponList(@RequestBody GenericRequest<PromotionCouponQueryParam> request);

    /**
     * 查询用户所领取的优惠券Id(promotionId)
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/promotion_coupon/promotion_coupon_ids")
    TMultiResult<Integer> searchPromotionIdListByMemberId(@RequestBody GenericRequest<PromotionCouponQueryParam> request);

    /**
     * 用户领取优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/promotion_coupon/create")
    TSingleResult<Boolean> addPromotionCoupon(@RequestBody GenericRequest<MemberCouponParam> request);

    /**
     * 用户领取兑换券
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/promotion_coupon/exchange")
    TSingleResult<Boolean> addExchangeCoupon(@RequestBody GenericRequest<MemberCouponParam> request);
}
