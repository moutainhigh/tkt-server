package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 15:52
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderDiscountDetailDTO extends BaseEntityInfo {

    private Integer discountDetailId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 优惠类型(1-折扣券, 2-抵现券, 3-兑换券)
     */
    private PromotionTypeEnum discountType;

    /**
     * 实际减免金额
     */
    private Money discountAmount;

    /**
     * 折扣比例
     */
    private Integer discountNumber;

    /**
     * 兑换商品ID
     */
    private Integer exchangeSkuId;

    /**
     * 优惠码
     */
    private String couponCode;

    /**
     * 状态(0-无效, 1-有效)
     */
    private Boolean status;

    /**
     * 优惠券ID
     */
    private Integer couponId;

    /**
     * 优惠说明
     */
    private String remark;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
