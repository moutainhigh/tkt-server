package com.mtl.cypw.stock.model;

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
@Table(name = "cy_stock")
public class Stock extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 商品类型（1-票品, 2-选座票品, 3-衍生品）
     */
    @Column(name = "sku_type")
    private Integer skuType;

    /**
     * 商品ID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 票档ID
     */
    @Column(name = "price_id")
    private Integer priceId;

    /**
     * 总库存
     */
    @Column(name = "total_stock")
    private Integer totalStock;

    /**
     * 已售库存
     */
    @Column(name = "selling_stock")
    private Integer sellingStock;

    /**
     * 预留总库存
     */
    @Column(name = "reserve_total_stock")
    private Integer reserveTotalStock;

    /**
     * 预留已售库存
     */
    @Column(name = "reserve_selling_stock")
    private Integer reserveSellingStock;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }

    public Integer getActualInventory() {
        return this.getTotalStock() - this.getSellingStock();
    }
}