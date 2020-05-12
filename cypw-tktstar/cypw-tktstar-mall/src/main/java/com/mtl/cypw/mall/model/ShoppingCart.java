package com.mtl.cypw.mall.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 11:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_shopping_cart")
public class ShoppingCart extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 商品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * SKU 类型
     */
    @Column(name = "sku_type")
    private Integer skuType;

    /**
     * SKU ID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 购买数量
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 购物车状态，0：失效，1：正常
     */
    @Column(name = "status")
    private Integer status;

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