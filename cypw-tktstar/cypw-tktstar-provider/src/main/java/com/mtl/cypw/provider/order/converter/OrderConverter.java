package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.juqitech.service.enums.PlatformSource;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.dto.OrderDTO;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderConverter extends DataConverter<Order, OrderDTO> {

    @Autowired
    private OrderItemConverter orderItemConverter;
    @Autowired
    private OrderRefundConverter orderRefundConverter;
    @Autowired
    private OrderTicketConverter orderTicketConverter;
    @Autowired
    private OrderDiscountConverter orderDiscountConverter;
    @Autowired
    private OrderDeliveryConverter orderDeliveryConverter;
    @Autowired
    private OrderSnapshotConverter orderSnapshotConverter;

    @Override
    public OrderDTO convert(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(String.valueOf(order.getId()));
        dto.setOrderId(order.getId());
        dto.setOrderNo(order.getOrderNo());
        dto.setOrderTitle(order.getOrderTitle());
        dto.setMobileNo(order.getMobileNo());
        dto.setOrderType(OrderTypeEnum.getObject(order.getOrderType()));
        dto.setOrderStatus(OrderStatusEnum.getObject(order.getOrderStatus()));
        dto.setMemberId(order.getMemberId());
        dto.setMtlUserId(order.getMtlUserId());
        dto.setEnterpriseId(order.getEnterpriseId());
        dto.setChannel(ChannelEnum.getObject(order.getChannelId()));
        dto.setVenueId(order.getVenueId());
        dto.setVenueName(order.getVenueName());
        dto.setProductId(order.getProductId());
        dto.setProductName(order.getProductName());
        dto.setEventId(order.getEventId());
        dto.setEventName(order.getEventName());

        dto.setDeliveryFee(MoneyUtils.getMoneyByCent(order.getDeliveryFee()));
        dto.setDelivery(orderDeliveryConverter.convert(order.getDelivery()));
        dto.setDeliverType(DeliverTypeEnum.getObject(order.getDeliverType()));

        dto.setGiftCardId(order.getGiftCardId());
        dto.setGiftCardAmount(MoneyUtils.getMoneyByCent(order.getGiftCardAmount()));
        dto.setPayType(PayTypeEnum.getObject(order.getPaymentTypeId()));
        dto.setPaidTime(order.getPaidTime());

        dto.setDiscountFee(MoneyUtils.getMoneyByCent(order.getDiscountFee()));
        dto.setDiscountDetails(orderDiscountConverter.batchConvert(order.getDiscountDetails()));

        dto.setOriginAmount(MoneyUtils.getMoneyByCent(order.getOriginAmount()));
        dto.setActualAmount(MoneyUtils.getMoneyByCent(order.getActualAmount()));
        dto.setTicketAmount(MoneyUtils.getMoneyByCent(order.getTicketAmount()));
        dto.setSettleAmount(MoneyUtils.getMoneyByCent(order.getSettleAmount()));

        dto.setFetchCode(order.getFetchCode());
        dto.setFetchQrcode(order.getFetchQrcode());

        dto.setOrderItems(orderItemConverter.batchConvert(order.getOrderItems()));
        dto.setOrderTickets(orderTicketConverter.batchConvert(order.getOrderTickets()));

        dto.setRefund(orderRefundConverter.convert(order.getRefund()));
        dto.setSnapshot(orderSnapshotConverter.convert(order.getOrderSnapshot()));
        dto.setSource(PlatformSource.getByCode(order.getPlatformId()));

        dto.setOrderTime(order.getOrderTime());
        dto.setLockedTime(order.getLockedTime());
        dto.setCancelTime(order.getCancelTime());
        dto.setExpireTime(order.getExpireTime());
        dto.setTicketedTime(order.getTicketedTime());
        dto.setConsumedTime(order.getConsumedTime());
        dto.setClientIp(order.getClientIp());
        dto.setEnterpriseId(order.getEnterpriseId());
        dto.setWechatAppId(order.getWechatAppId());
        dto.setWechatOpenId(order.getWechatOpenId());
        dto.setTransactionFlowNo(order.getTransactionFlowNo());
        dto.setRemark(order.getRemark());
        return dto;
    }

}
