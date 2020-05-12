package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.utils.Money;
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
public class OrderItemDTO extends BaseEntityInfo {

    /**
     * 自增ID
     */
    private Integer orderItemId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 商品名称
     */
    private String productTitle;

    /**
     * 明细ID（场次/商品）
     */
    private Integer itemId;

    /**
     * sku类型(1-普通票价, 2-座位售卖, 3-商品售卖)
     */
    private SkuTypeEnum skuType;

    /**
     * 票价ID（skuId）
     */
    private Integer priceId;

    /**
     * 价格描述
     */
    private String priceDesc;

    /**
     * 详情图片（兼容衍生品）
     */
    private String imageSrc;

    /**
     * 是否套票
     */
    private Boolean isPackage;

    /**
     * 每份数量(普通票价固定为1, 套票为动态设置)
     */
    private Integer packageNumber;

    /**
     * 市场价
     */
    private Money originPrice;

    /**
     * 单价
     */
    private Money unitPrice;

    /**
     * 商品个数
     */
    private Integer quantity;

    /**
     * 优惠金额
     */
    private Money discountFee;

    /**
     * 实付金额（unit_price * quantity - discount_fee）
     */
    private Money costFee;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
