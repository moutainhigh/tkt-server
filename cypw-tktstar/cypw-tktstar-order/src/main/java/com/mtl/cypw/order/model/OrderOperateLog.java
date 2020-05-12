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
@Table(name = "cy_order_operate_log")
public class OrderOperateLog extends BaseModel {
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
     * 操作人
     */
    @Column(name = "operator_id")
    private Integer operatorId;

    /**
     * 操作人姓名
     */
    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 操作类型()
     */
    @Column(name = "operate_type")
    private String operateType;

    /**
     * 订单变更前状态
     */
    @Column(name = "before_status")
    private String beforeStatus;

    /**
     * 订单变更后状态
     */
    @Column(name = "after_status")
    private String afterStatus;

    /**
     * 相关日志
     */
    @Column(name = "record_json")
    private String recordJson;

    /**
     * 备注
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