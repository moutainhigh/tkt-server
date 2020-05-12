package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-19 11:24
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderPaperDTO extends BaseEntityInfo {

    /**
     * 是否验证通过
     */
    private Boolean passed;

    /**
     * 验证提示消息
     */
    private String passMessage;

    /**
     * 操作人ID
     */
    private Integer fetchUserId;

    /**
     * 操作人姓名
     */
    private String fetchUserName;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 购买手机号
     */
    private String mobileNo;

    /**
     * 订单总额
     */
    private Money originAmount;

    /**
     * 实际支付总额
     */
    private Money actualAmount;

    /**
     * 商品价值总额
     */
    private Money ticketAmount;

    /**
     * 礼品卡消费总额
     */
    private Money giftCardAmount;

    /**
     * 优惠总金额
     */
    private Money discountFee;

    /**
     * 订单类型(1-票品订单, 2-衍生品订单)
     */
    private OrderTypeEnum orderType;

    /**
     * 订单状态()
     */
    private OrderStatusEnum orderStatus;

    /**
     * 交付方式()
     */
    private DeliverTypeEnum deliverType;

    /**
     * 支付方式()
     */
    private PayTypeEnum payType;

    /**
     * 订单取票码（脱敏）
     */
    private String fetchCode;

    /**
     * 下单渠道
     */
    private ChannelEnum channel;

    /**
     * 订单生成时间
     */
    private Date orderTime;

    /**
     * 支付成功时间
     */
    private Date paidTime;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 商品明细
     */
    List<FetchItemPaperDTO> itemPapers;

    /**
     * 订单票券
     */
    List<FetchTicketPaperDTO> ticketPapers;

}
