package com.mtl.cypw.domain.mall.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-03 11:41
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ShoppingCartDTO extends BaseEntityInfo {

    /**
     * 记录 ID
     */
    private Integer shoppingCartId;

    /**
     * 用户 ID
     */
    private Integer memberId;

    /**
     * 商品 ID
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品链接
     */
    private String productImgSrc;

    /**
     * SKU 类型
     */
    private Integer skuType;

    /**
     * SKU ID
     */
    private Integer skuId;

    /**
     * SKU 名称
     */
    private String skuName;

    /**
     * 售卖价（单价）
     */
    private BigDecimal unitPrice;

    /**
     * 指导价（原价）
     */
    private BigDecimal originalPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 每单限制数
     */
    private Integer limitCnt;

    /**
     * 购物车状态，0：失效，1：正常
     */
    private Integer status;

    /**
     * 企业 ID
     */
    private Integer enterpriseId;

}
