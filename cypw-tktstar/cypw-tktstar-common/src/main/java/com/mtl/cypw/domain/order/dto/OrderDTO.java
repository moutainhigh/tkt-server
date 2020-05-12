package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.juqitech.service.enums.PlatformSource;
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
 * @date 2019-11-25 13:56
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderDTO extends BaseEntityInfo {

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
     * 订单结算总额
     */
    private Money settleAmount;

    /**
     * 快递费
     */
    private Money deliveryFee;

    /**
     * 优惠总金额
     */
    private Money discountFee;

    /**
     * 订单类型(1-票品订单, 2-衍生品订单, 3-票品及衍生品组合订单)
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
     * 订单二维码（脱敏）
     */
    private String fetchQrcode;

    /**
     * 订单取票码（脱敏）
     */
    private String fetchCode;

    /**
     * 摩天轮用户ID
     */
    private String mtlUserId;

    /**
     * 下单会员ID
     */
    private Integer memberId;

    /**
     * 下单手机号
     */
    private String mobileNo;

    /**
     * 用户微信OpenId
     */
    private String wechatOpenId;

    /**
     * 用户微信AppId
     */
    private String wechatAppId;

    /**
     * 下单IP地址
     */
    private String clientIp;

    /**
     * 下单渠道
     */
    private ChannelEnum channel;

    /**
     * 下单平台
     */
    private PlatformSource source;

    /**
     * 优惠活动ID
     */
    private Integer promotionId;

    /**
     * 礼品卡ID
     */
    private Integer giftCardId;

    /**
     * 订单生成时间
     */
    private Date orderTime;

    /**
     * 订单取消时间
     */
    private Date cancelTime;

    /**
     * 支付成功时间
     */
    private Date paidTime;

    /**
     * 订单过期时间
     */
    private Date expireTime;

    /**
     * 锁座成功时间
     */
    private Date lockedTime;

    /**
     * 出票成功时间
     */
    private Date ticketedTime;

    /**
     * 入场核销时间
     */
    private Date consumedTime;

    /**
     * 项目ID(兼容衍生品)
     */
    private Integer productId;

    /**
     * 项目名称(兼容衍生品)
     */
    private String productName;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 场次名称
     */
    private String eventName;

    /**
     * 场馆ID
     */
    private Integer venueId;

    /**
     * 场馆名称
     */
    private String venueName;

    /**
     * 交易流水号
     */
    private String transactionFlowNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 订单商品明细
     */
    private List<OrderItemDTO> orderItems;

    /**
     * 订单票品明细
     */
    private List<OrderTicketDTO> orderTickets;

    /**
     * 订单优惠列表
     */
    private List<OrderDiscountDetailDTO> discountDetails;

    /**
     * 订单退款记录
     */
    private OrderRefundDTO refund;

    /**
     * 订单交付方式
     */
    private OrderDeliveryDTO delivery;

    /**
     * 订单交易快照
     */
    private OrderTransactionSnapshotDTO snapshot;
}
