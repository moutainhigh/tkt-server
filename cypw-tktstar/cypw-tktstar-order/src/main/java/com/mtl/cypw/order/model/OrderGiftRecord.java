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
@Table(name = "cy_order_gift_record")
public class OrderGiftRecord extends BaseModel {
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
     * 赠送类型（1-普通,2-赠票,3-工作票）
     */
    @Column(name = "gift_type")
    private Integer giftType;

    /**
     * 赠送标识
     */
    @Column(name = "gift_flag")
    private String giftFlag;

    /**
     * 赠送说明
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