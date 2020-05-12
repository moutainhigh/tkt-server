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
 * @date 2020-01-16 19:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_system_record")
public class OrderSystemRecord extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 线下出单类型（1-普通,2-预留出票,3-票池出票）
     */
    @Column(name = "action_type")
    private Integer actionType;

    /**
     * 项目名称(兼容衍生品)
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品名称(兼容衍生品)
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 操作人ID
     */
    @Column(name = "operator_id")
    private Integer operatorId;

    /**
     * 操作人名称
     */
    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 订单总金额（分）
     */
    @Column(name = "total_amount")
    private Integer totalAmount;

    /**
     * 支付总金额（分）
     */
    @Column(name = "pay_amount")
    private Integer payAmount;

    /**
     * 商品总数
     */
    @Column(name = "item_count")
    private Integer itemCount;

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