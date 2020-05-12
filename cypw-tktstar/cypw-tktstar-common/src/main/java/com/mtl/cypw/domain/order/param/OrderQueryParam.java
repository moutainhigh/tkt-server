package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 订单查询
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@Builder
public class OrderQueryParam extends BaseParam {

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 商户ID
     */
    private Integer enterpriseId;

    /**
     * 项目ID
     */
    private Integer productId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单状态
     */
    private List<Integer> orderStatusList;

    /**
     * 是否包含订单明细
     */
    private boolean isIncludingOrderItems;

    /**
     * 是否包含演出票券
     */
    private boolean isIncludingOrderTickets;

    /**
     * 是否包含优惠信息
     */
    private boolean isIncludingDiscountDetails;

    /**
     * 是否包含订单快照
     */
    private boolean isIncludingSnapshot;

    /**
     * 是否包含退款信息
     */
    private boolean isIncludingOrderRefund;

    /**
     * 是否包含物流方式
     */
    private boolean isIncludingOrderDelivery;

    /**
     * 是否包含订单操作日志
     */
    private boolean isIncludingOrderOperationLog;


    @Override
    public void checkParam() {

    }
}
