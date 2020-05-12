package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;

/**
 * 退款
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderRefundParam extends BaseParam {

    /**
     * 订单ID
     */
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
    private Integer dutyType;

    /**
     * 是否回滚库存(0-不需要,1-需要)
     */
    private Integer rollbackStock;

    /**
     * 退款金额类型(1-全额退款, 2-部分退款)
     */
    private Integer refundType;

    /**
     * 退款金额(分)
     */
    private Long refundAmount;

    /**
     * 退款原因描述
     */
    private String reasonNote;


    @Override
    public void checkParam() {
        ParamChecker.notNull(orderId, "订单编号不能为空");
    }
}
