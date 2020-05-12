package com.mtl.cypw.order.model;

import com.google.common.collect.Sets;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.enums.SystemActionTypeEnum;
import com.mtl.cypw.domain.order.param.OrderCouponParam;
import com.mtl.cypw.domain.order.param.UniformSeatParam;
import com.mtl.cypw.domain.order.param.UniformSeatTicketParam;
import com.mtl.cypw.domain.order.param.UniformSkuParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order")
public class Order extends BaseModel {
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
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单标题
     */
    @Column(name = "order_title")
    private String orderTitle;

    /**
     * 订单总额（分）
     */
    @Column(name = "origin_amount")
    private Long originAmount;

    /**
     * 实际支付总额（分）
     * {actualAmount = (ticketAmount + deliveryFee) - discountFee - giftCardAmount}
     */
    @Column(name = "actual_amount")
    private Long actualAmount;

    /**
     * 商品价值总额（分）
     */
    @Column(name = "ticket_amount")
    private Long ticketAmount;

    /**
     * 礼品卡消费总额（分）
     */
    @Column(name = "gift_card_amount")
    private Long giftCardAmount;

    /**
     * 订单结算总额（分）
     */
    @Column(name = "settle_amount")
    private Long settleAmount;

    /**
     * 快递费（分）
     */
    @Column(name = "delivery_fee")
    private Long deliveryFee;

    /**
     * 优惠总金额（分）
     */
    @Column(name = "discount_fee")
    private Long discountFee;

    /**
     * 订单类型(1-票品订单, 2-衍生品订单)
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 订单状态()
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 交付方式()
     */
    @Column(name = "deliver_type")
    private Integer deliverType;

    /**
     * 支付方式()
     */
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;

    /**
     * 订单二维码
     */
    @Column(name = "fetch_qrcode")
    private String fetchQrcode;

    /**
     * 订单取票码
     */
    @Column(name = "fetch_code")
    private String fetchCode;

    /**
     * 摩天轮用户ID
     */
    @Column(name = "mtl_user_id")
    private String mtlUserId;

    /**
     * 下单会员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 下单手机号
     */
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * 用户微信OpenId
     */
    @Column(name = "wechat_open_id")
    private String wechatOpenId;

    /**
     * 用户微信AppId
     */
    @Column(name = "wechat_app_id")
    private String wechatAppId;

    /**
     * 下单IP地址
     */
    @Column(name = "client_ip")
    private String clientIp;

    /**
     * 下单渠道
     */
    @Column(name = "channel_id")
    private Integer channelId;

    /**
     * 下单平台
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 优惠活动ID
     */
    @Column(name = "promotion_id")
    private Integer promotionId;

    /**
     * 礼品卡ID
     */
    @Column(name = "gift_card_id")
    private Integer giftCardId;

    /**
     * 订单生成时间
     */
    @Column(name = "order_time")
    private Date orderTime;

    /**
     * 订单取消时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 支付成功时间
     */
    @Column(name = "paid_time")
    private Date paidTime;

    /**
     * 订单过期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 锁座成功时间
     */
    @Column(name = "locked_time")
    private Date lockedTime;

    /**
     * 出票成功时间
     */
    @Column(name = "ticketed_time")
    private Date ticketedTime;

    /**
     * 入场核销完成时间
     */
    @Column(name = "consumed_time")
    private Date consumedTime;

    /**
     * 项目ID(兼容衍生品)
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 项目名称(兼容衍生品)
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 场次名称
     */
    @Column(name = "event_name")
    private String eventName;

    /**
     * 场馆ID
     */
    @Column(name = "venue_id")
    private Integer venueId;

    /**
     * 场馆名称
     */
    @Column(name = "venue_name")
    private String venueName;

    /**
     * 交易流水号
     */
    @Column(name = "transaction_flow_no")
    private String transactionFlowNo;

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

    private boolean needPush;
    private boolean needCheckBuyLimit;
    private boolean needSendPaySuccessMsg;
    private boolean needSendTicketIssueMsg;
    private boolean needSendConsumedMsg;

    //【下单】选购座位列表
    private List<UniformSeatTicketParam> uniformSeatTicketParams;

    //【下单】选购商品列表
    private List<UniformSkuParam> uniformSkuParams;

    //【下单】选购商品优惠列表
    private List<OrderCouponParam> orderCouponParams;

    //【下单】系统出票类型
    private SystemActionTypeEnum systemActionType;

    /**
     * 订单商品明细
     */
    private List<OrderItem> orderItems;

    /**
     * 订单票品明细
     */
    private List<OrderTicket> orderTickets;

    /**
     * 订单优惠列表
     */
    private List<OrderDiscountDetail> discountDetails;

    /**
     * 订单交付方式
     */
    private OrderDelivery delivery;

    /**
     * 订单退款信息
     */
    private OrderRefund refund;

    /**
     * 库存消耗记录
     */
    private OrderInventoryConsumeRecord inventoryConsumeRecord;

    /**
     * 订单交易快照
     */
    private OrderSnapshot orderSnapshot;

    /**
     * 订单增票记录
     */
    private OrderGiftRecord orderGiftRecord;

    /**
     * 系统出票记录
     */
    private OrderSystemRecord orderSystemRecord;

    /**
     * 是否包含选座订单
     * @return
     */
    public boolean isIncludeSeat() {
        return CollectionUtils.isNotEmpty(this.uniformSeatTicketParams);
    }

    /**
     * 仅选座订单
     * @return
     */
    public boolean isOnlySeat() {
        return isIncludeSeat() && isTicket();
    }

    /**
     * 票品订单
     * @return
     */
    public boolean isTicket() {
        return OrderTypeEnum.ONLY_TICKET.getCode() == this.orderType;
    }

    /**
     * 衍生品订单
     * @return
     */
    public boolean isGoods() {
        return OrderTypeEnum.ONLY_GOODS.getCode() == this.orderType;
    }

    public boolean isSystemOrder() {
        return this.channelId == ChannelEnum.BACKEND.getCode();
    }

    public boolean isReserveOrder() {
        return isSystemOrder() && SystemActionTypeEnum.RESERVE_ORDER.getCode() == this.systemActionType.getCode();
    }

    public boolean isPoolOrder() {
        return isSystemOrder() && SystemActionTypeEnum.POOL_ORDER.getCode() == this.systemActionType.getCode();
    }

    public boolean canPay() {
        return this.orderStatus == OrderStatusEnum.LOCKED.getCode()
                && this.getExpireTime() != null
                && this.getExpireTime().after(new Date());
    }

    public void payFailed() {
        this.setOrderStatus(OrderStatusEnum.PAY_FAILED.getCode());
        this.setNeedPush(true);
        this.setNeedCheckBuyLimit(true);
    }

    public void paySuccess(Date userPayTime) {
        this.setPaidTime(userPayTime);
        this.setOrderStatus(OrderStatusEnum.PAID.getCode());
        this.setNeedPush(true);
    }

    public void lockSuccess(Date lockedTime, int minutesToExpire) {
        this.setOrderStatus(OrderStatusEnum.LOCKED.getCode());
        this.setLockedTime(lockedTime);
        this.setExpireTime(DateTime.now().plusMinutes(minutesToExpire).toDate());
        this.setNeedPush(true);
        this.setNeedCheckBuyLimit(true);
    }

    public void lockFailed() {
        this.setOrderStatus(OrderStatusEnum.LOCK_FAILED.getCode());
    }

    public boolean canCancel() {
        return this.getOrderStatus() < OrderStatusEnum.PAID.getCode();
    }

    public boolean isCanceled() {
        return OrderStatusEnum.CANCELED.getCode() == this.getOrderStatus()
                || OrderStatusEnum.CANCELED.getCode() == this.getOrderStatus();
    }

    public boolean isPaymentTimeout() {
        return this.getOrderStatus() == OrderStatusEnum.LOCKED.getCode()
                && this.expireTime != null
                && new DateTime(this.expireTime).plusMinutes(1).isBefore(DateTime.now());
    }

    public boolean isOwned(Integer userId) {
        return this.memberId != null && this.memberId.equals(userId);
    }

    public void cancel() {
        this.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        this.setCancelTime(DateUtils.now());
        this.setNeedPush(true);
        this.setNeedCheckBuyLimit(true);
    }

    public void timeoutCancel() {
        this.setOrderStatus(OrderStatusEnum.EXPIRED.getCode());
        this.setCancelTime(DateUtils.now());
        this.setNeedPush(true);
        this.setNeedCheckBuyLimit(true);
    }

    public void startTicket() {
        this.setOrderStatus(OrderStatusEnum.TICKETING.getCode());
        this.setNeedPush(true);
        this.setNeedSendPaySuccessMsg(true);
    }

    public void ticketSuccess(Date successTime) {
        this.setOrderStatus(OrderStatusEnum.TICKETED.getCode());
        this.setTicketedTime(successTime);
        this.setNeedPush(true);
        this.setNeedSendTicketIssueMsg(true);
    }

    public void ticketFailed() {
        this.setOrderStatus(OrderStatusEnum.TICKET_FAILED.getCode());
        this.setNeedPush(true);
        this.setNeedCheckBuyLimit(true);
        this.setNeedSendTicketIssueMsg(true);
    }

    public boolean isLocked() {
        return this.getOrderStatus() == OrderStatusEnum.LOCKED.getCode() && this.getLockedTime() != null;
    }

    public boolean isPaid() {
        return this.getOrderStatus() >= OrderStatusEnum.PAID.getCode() && this.getPaidTime() != null;
    }

    public boolean isWaitToTicket() {
        return this.getOrderStatus() == OrderStatusEnum.PAID.getCode()
                && this.getPaidTime() != null
                && this.delivery != null
                && (this.delivery.getDeliveryStatus() == DeliverStatusEnum.UNDELIVERED.getCode()
                        || this.delivery.getDeliveryStatus() == DeliverStatusEnum.DELIVERING.getCode());
    }

    public boolean isTicketing() {
        return this.getOrderStatus() == OrderStatusEnum.TICKETING.getCode();
    }


    public boolean isTicketed() {
        return this.getOrderStatus() == OrderStatusEnum.TICKETED.getCode() && this.getTicketedTime() != null;
    }

    public void delivered() {
        Assert.notNull(this.getDelivery(), "Null Delivery");
        this.setOrderStatus(OrderStatusEnum.DELIVERED.getCode());
        this.delivery.setDeliverTime(new Date());
        this.delivery.setDeliveredTime(new Date());
        this.delivery.setDeliveryStatus(DeliverStatusEnum.DELIVERED.getCode());
    }

    /**
     * 获取所有商品数量
     * @return
     */
    public int getSkuQuantity() {
        if (CollectionUtils.isEmpty(this.getUniformSkuParams())) {
            return 0;
        }
        AtomicInteger count = new AtomicInteger();
        this.getUniformSkuParams().stream().map(sku -> count.addAndGet(sku.getCount())) ;
        return count.get();
    }

    /**
     * 获取选座所有座位列表
     * @return
     */
    public Set<Integer> getSeatIdSet() {
        Set<Integer> seatIds = Sets.newHashSet();
        if (!isOnlySeat()) {
            return seatIds;
        }
        if (CollectionUtils.isNotEmpty(this.getOrderTickets())) {
            this.getOrderTickets().stream().forEach(orderTicket -> seatIds.add(orderTicket.getSeatId()));
        } else {
            Assert.isTrue(CollectionUtils.isNotEmpty(this.uniformSeatTicketParams),"seatTicketParams is empty.");
            for (UniformSeatTicketParam seatTicketParam : this.uniformSeatTicketParams) {
                List<UniformSeatParam> seats = seatTicketParam.getSeats();
                Assert.isTrue(CollectionUtils.isNotEmpty(seats),"seats is empty.");
                for(UniformSeatParam seat : seats) {
                    seatIds.add(seat.getSeatId());
                }
            }
        }
        return seatIds;
    }

    /**
     * 配送中的订单
     * @return
     */
    public boolean isDelivering() {
        return OrderStatusEnum.DELIVERING.getCode() == this.getOrderStatus();
    }

    /**
     * 待发货的订单
     * @return
     */
    public boolean isDelivered() {
        return OrderStatusEnum.TICKETED.getCode() == this.getOrderStatus();
    }

    /**
     * 绑定物流单号成功
     */
    public void bindExpressNoSuccess() {
        this.setOrderStatus(OrderStatusEnum.DELIVERING.getCode());
    }
}