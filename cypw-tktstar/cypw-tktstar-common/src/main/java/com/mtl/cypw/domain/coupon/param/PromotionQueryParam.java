package com.mtl.cypw.domain.coupon.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Data
public class PromotionQueryParam {

    private Integer enterpriseId;

    private Integer programId;

    private Integer businessTypeId;

    /**
     * 1：折扣券，2:现金券，3:兑换券
     */
    private List<Integer> promotionTypeList;

    /**
     * 0：全部演出，1：指定分类，2：指定演出
     */
    private List<Integer> notPromotionPointTypes;

    /**
     * 指定演出分类集，与 programPromotionIds 条件取并集
     */
    private List<Integer> programTypeIds;

    /**
     * 指定演出时,演出能使用的优惠券id集合
     * 与 programTypeIds 条件取并集
     */
    private List<Integer> programPromotionIds;

    /**
     * 优惠id集合
     */
    private List<Integer> promotionIds;

    /**
     * 需要排除的优惠id集合
     */
    private List<Integer> notPromotionIds;

    /**
     * 是否为可领取时间
     */
    private Integer isReceiveTime;

    /**
     * 订单金额限制
     */
    private Double orderAmountRestriction;

    /**
     * 0：线上领券，1:线下发券
     */
    private Integer distributionType;

    /**
     * 当前时间是否可用
     */
    private Integer isAvailableTime;

}
