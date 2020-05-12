package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_refund")
public class OrderRefund extends BaseModel {
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
     * 退款发起人类型
     */
    @Column(name = "apply_type")
    private Integer applyType;

    /**
     * 退款原因类型()
     */
    @Column(name = "reason_type")
    private Integer reasonType;

    /**
     * 退款责任类型(1-用户责任, 2-商户责任)
     */
    @Column(name = "duty_type")
    private Integer dutyType;

    /**
     * 退款状态(0-未提交, 1-审批中, 2-已完成)
     */
    @Column(name = "refund_status")
    private Integer refundStatus;

    /**
     * 退款流程完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 是否回滚库存(0-不需要,1-需要)
     */
    @Column(name = "rollback_stock")
    private Integer rollbackStock;

    /**
     * 退款操作人ID
     */
    @Column(name = "operator_id")
    private String operatorId;

    /**
     * 退款操作人姓名
     */
    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 退款金额类型(1-全额退款, 2-部分退款)
     */
    @Column(name = "refund_type")
    private Integer refundType;

    /**
     * 退款金额(分)
     */
    @Column(name = "refund_amount")
    private Long refundAmount;

    /**
     * 退款原因描述
     */
    @Column(name = "reason_note")
    private String reasonNote;

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