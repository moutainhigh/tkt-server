package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_discount_detail")
public class OrderDiscountDetail extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 优惠类型(0-线下优惠, 1-折扣券, 2-抵现券, 3-兑换券)
     */
    @Column(name = "discount_type")
    private Integer discountType;

    /**
     * 实际减免金额
     */
    @Column(name = "discount_amount")
    private Long discountAmount;

    /**
     * 折扣比例
     */
    @Column(name = "discount_number")
    private Integer discountNumber;

    /**
     * 兑换商品ID
     */
    @Column(name = "exchange_sku_id")
    private Integer exchangeSkuId;

    /**
     * 优惠码
     */
    @Column(name = "coupon_code")
    private String couponCode;

    /**
     * 状态(0-无效, 1-有效)
     */
    @Column(name = "Status")
    private Integer status;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 优惠说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }
}