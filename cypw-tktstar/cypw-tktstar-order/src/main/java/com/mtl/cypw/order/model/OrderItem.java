package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_item")
public class OrderItem extends BaseModel {
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
     * 商品名称
     */
    @Column(name = "product_title")
    private String productTitle;

    /**
     * 明细ID（场次/商品）
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * sku类型(1-普通票价, 2-座位售卖, 3-商品售卖)
     */
    @Column(name = "sku_type")
    private Integer skuType;

    /**
     * 票价ID（skuId）
     */
    @Column(name = "price_id")
    private Integer priceId;

    /**
     * 价格描述
     */
    @Column(name = "price_desc")
    private String priceDesc;

    /**
     * 详情图片（兼容衍生品）
     */
    @Column(name = "image_src")
    private String imageSrc;

    /**
     * 是否套票(0-普通票, 1-套票)
     */
    @Column(name = "is_package")
    private Integer isPackage;

    /**
     * 每份数量(普通票价固定为1, 套票为动态设置)
     */
    @Column(name = "package_number")
    private Integer packageNumber;

    /**
     * 市场价(分)
     */
    @Column(name = "origin_price")
    private Long originPrice;

    /**
     * 单价(分)
     */
    @Column(name = "unit_price")
    private Long unitPrice;

    /**
     * 商品个数
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 优惠金额
     */
    @Column(name = "discount_fee")
    private Long discountFee;

    /**
     * 实付金额（unit_price * quantity - discount_fee）
     */
    @Column(name = "cost_fee")
    private Long costFee;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }

    public List<OrderTicket> orderTickets;

    public boolean isTicket() {
        return SkuTypeEnum.TICKET.getCode() == skuType;
    }

    public boolean isGoods() {
        return SkuTypeEnum.GOODS.getCode() == skuType;
    }
}