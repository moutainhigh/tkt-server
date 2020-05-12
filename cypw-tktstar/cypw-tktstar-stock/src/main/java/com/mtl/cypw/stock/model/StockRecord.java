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
@Table(name = "cy_stock_record")
public class StockRecord extends BaseModel {
    /**
     * 自增主键
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
     * 流水号
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 用户ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 记录状态（1-扣库存成功, 2回滚成功）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * SKU详情
     */
    @Column(name = "sku_info")
    private String skuInfo;

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
}