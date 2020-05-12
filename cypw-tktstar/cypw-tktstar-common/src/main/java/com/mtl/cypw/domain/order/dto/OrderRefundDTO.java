package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 15:52
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderRefundDTO extends BaseEntityInfo {

    /**
     * 退款ID
     */
    private Integer refundId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 退款发起人类型
     */
    private Integer applyType;

    /**
     * 退款原因类型()
     */
    private Integer reasonType;

    /**
     * 退款责任类型(1-用户责任, 2-商户责任)
     */
    private Integer dutyType;

    /**
     * 退款状态(0-未提交, 1-审批中, 2-已完成)
     */
    private Integer refundStatus;

    /**
     * 退款流程完成时间
     */
    private Date finishTime;

    /**
     * 是否回滚库存(0-不需要,1-需要)
     */
    private Boolean rollbackStock;

    /**
     * 退款操作人ID
     */
    private String operatorId;

    /**
     * 退款操作人姓名
     */
    private String operatorName;

    /**
     * 退款金额类型(1-全额退款, 2-部分退款)
     */
    private Integer refundType;

    /**
     * 退款金额
     */
    private Money refundAmount;

    /**
     * 退款原因描述
     */
    private String reasonNote;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
    
}
