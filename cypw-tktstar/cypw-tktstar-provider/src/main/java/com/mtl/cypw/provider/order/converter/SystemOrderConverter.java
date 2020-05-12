package com.mtl.cypw.provider.order.converter;

import com.google.common.collect.Lists;
import com.juqitech.converter.DataConverter;
import com.juqitech.service.enums.PlatformSource;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.util.BigDecimalUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.common.utils.SnowflakeUtils;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import com.mtl.cypw.domain.order.enums.ConsumeTypeEnum;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.param.OrderDiscountParam;
import com.mtl.cypw.domain.order.param.OrderGiftParam;
import com.mtl.cypw.domain.order.param.RecipientAddressParam;
import com.mtl.cypw.domain.order.param.SystemCreateOrderParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDelivery;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.model.OrderGiftRecord;
import com.mtl.cypw.order.model.OrderInventoryConsumeRecord;
import com.mtl.cypw.order.model.OrderSnapshot;
import com.mtl.cypw.provider.order.service.uniform.UniformSkuService;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.FetchTicketWay;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.FetchTicketWayService;
import com.mtl.cypw.show.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-02 15:54
 */
@Slf4j
@Component
public class SystemOrderConverter extends DataConverter<SystemCreateOrderParam, Order> {

    @Autowired
    private FetchTicketWayService fetchTicketWayService;

    @Autowired
    private UniformSkuService uniformSkuService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private EventService eventService;

    @Override
    public Order convert(SystemCreateOrderParam source) {
        Assert.notNull(source, "SystemCreateOrderParam is null");
        Order order = new Order();
        Date currentDate = new Date();
        order.setOrderTitle(source.getOrderType().getName() + "订单");
        order.setOrderTime(currentDate);
        order.setCreateTime(currentDate);
        order.setOrderType(source.getOrderType().getCode());
        order.setSystemActionType(source.getActionType());
        order.setMobileNo(source.getRecipientMobile());
        if (source.getMemberId() != null) {
            order.setMemberId(source.getMemberId());
        } else {
            order.setMemberId(0);
        }
        order.setClientIp("127.0.0.1");
        order.setEnterpriseId(source.getEnterpriseId());
        order.setChannelId(ChannelEnum.BACKEND.getCode());
        order.setOrderStatus(OrderStatusEnum.INIT.getCode());
        order.setPlatformId(PlatformSource.WEB.getCode());
        if (source.getTotalPrice() != null) {
            order.setActualAmount(source.getTotalPrice().getCent());
            order.setTicketAmount(source.getTotalPrice().getCent());
            order.setOriginAmount(source.getTotalPrice().getCent());
        }
        // 选座参数
        if (CollectionUtils.isNotEmpty(source.getUniformSeatRequests())) {
            order.setUniformSeatTicketParams(source.getUniformSeatRequests());
        }
        // 商品参数
        if (CollectionUtils.isNotEmpty(source.getSkuRequests())) {
            order.setUniformSkuParams(source.getSkuRequests());
        }

        // 生成订单号
        if (StringUtils.isBlank(order.getOrderNo())) {
            String orderNo = SnowflakeUtils.getOrderNo();
            order.setOrderNo(orderNo);
        }

        // 订单快照信息（衍生品暂时以 items 聚合）
        if (OrderTypeEnum.ONLY_TICKET.getCode() == order.getOrderType()) {
            setOrderSnapshot(source, order);
        }

        // 订单交付方式
        setOrderDelivery(source, order);

        // 订单优惠信息
        if (source.hasDiscount() || source.hasGiftWithDiscount()) {
            setOrderDiscountDetails(source, order);
        }

        // 订单赠送信息
        if (source.hasGift()) {
            setOrderGiftRecord(source, order);
        }

        // 订单明细信息
        setOrderItemsAndTickets(source, order);

        // 订单消耗库存记录
        setStockConsumeRecord(source, order);
        return order;
    }

    private void setOrderSnapshot(SystemCreateOrderParam source, Order order) {
        Assert.notNull(source.getShowId(), "evenId is null");
        Event event = eventService.findOneById(source.getShowId());
        Assert.notNull(event, "event is null");
        Program program = programService.getProgramById(event.getProgramId());
        OrderSnapshot snapshot = new OrderSnapshot();
        snapshot.setVenueId(program.getVenueId());
        snapshot.setVenueName(program.getLocationName());
        snapshot.setTheatreName(program.getLocationName());
        snapshot.setTheatreAddress(program.getLocationAddress());
        snapshot.setProductId(program.getProgramId());
        snapshot.setProductName(program.getProgramTitle());
        snapshot.setProgramNotice(program.getProgramNotice());
        snapshot.setEventId(event.getEventId());
        String showName = new SimpleDateFormat("yyyy-MM-dd EEE HH:mm", Locale.CHINA).format(event.getEventDate());
        snapshot.setEventName(StringUtils.isBlank(event.getEventTitle()) ? showName : event.getEventTitle());
        snapshot.setShowName(showName);
        snapshot.setEnterpriseId(source.getEnterpriseId());
        snapshot.setListPosterUrl(program.getListImage());
        snapshot.setDetailPosterUrl(program.getDetailImage());
        order.setOrderSnapshot(snapshot);
        order.setEnterpriseId(source.getEnterpriseId());
        order.setProductName(snapshot.getProductName());
        order.setProductId(snapshot.getProductId());
        order.setEventName(snapshot.getEventName());
        order.setEventId(snapshot.getEventId());
        order.setVenueName(snapshot.getVenueName());
        order.setVenueId(snapshot.getVenueId());
    }

    private void setStockConsumeRecord(SystemCreateOrderParam source, Order order) {
        OrderInventoryConsumeRecord consumeRecord = new OrderInventoryConsumeRecord();
        consumeRecord.setEnterpriseId(order.getEnterpriseId());
        consumeRecord.setMemberId(order.getMemberId());
        consumeRecord.setEventId(order.getEventId());
        if (order.isGoods()) {
            consumeRecord.setConsumeType(ConsumeTypeEnum.GOODS.getCode());
        } else if (order.isOnlySeat()) {
            consumeRecord.setConsumeType(ConsumeTypeEnum.SEAT_TICKET.getCode());
        } else {
            consumeRecord.setConsumeType(ConsumeTypeEnum.TICKET.getCode());
        }
        order.setInventoryConsumeRecord(consumeRecord);
    }

    private void setOrderDiscountDetails(SystemCreateOrderParam source, Order order) {
        OrderDiscountDetail discountDetail = new OrderDiscountDetail();
        discountDetail.setCouponCode("OFFLINE");
        discountDetail.setCouponId(1);
        discountDetail.setDiscountType(PromotionTypeEnum.OFFLINE.getCode());
        discountDetail.setStatus(CommonStateEnum.VALID.getCode());
        discountDetail.setEnterpriseId(order.getEnterpriseId());
        OrderDiscountParam discountParam = source.getOrderDiscountParam();
        if (discountParam != null) {
            BigDecimal discountRate = discountParam.getDiscountRate();
            BigDecimal orderTicketAmount = BigDecimal.valueOf(order.getTicketAmount());
            BigDecimal afterDiscountAmount;
            if (discountRate != null) {
                afterDiscountAmount = orderTicketAmount.multiply(discountRate);
                discountDetail.setDiscountAmount(BigDecimalUtils.rounded2Long(orderTicketAmount.subtract(afterDiscountAmount)));
                discountDetail.setDiscountNumber(discountRate.multiply(new BigDecimal(100)).intValue());
            } else if (discountParam.getDiscountAmount() != null) {
                discountDetail.setDiscountAmount(new Money(discountParam.getDiscountAmount()).getCent());
            }
            discountDetail.setRemark(discountParam.getDiscountNote());
        }
        if (source.hasGiftWithDiscount()) {
            discountDetail.setDiscountAmount(source.getTotalPrice().getCent());
        }

        order.setDiscountDetails(Lists.newArrayList(discountDetail));
    }

    private void setOrderGiftRecord(SystemCreateOrderParam source, Order order) {
        if (source.hasGift()) {
            OrderGiftParam giftParam = source.getOrderGiftParam();
            OrderGiftRecord giftRecord = new OrderGiftRecord();
            giftRecord.setGiftType(giftParam.getGiftType().getCode());
            giftRecord.setGiftFlag(giftParam.getFlag());
            giftRecord.setRemark(giftParam.getRemark());
            order.setOrderGiftRecord(giftRecord);
        }
    }

    private void setOrderItemsAndTickets(SystemCreateOrderParam source, Order order) {
        if (CollectionUtils.isEmpty(order.getUniformSkuParams())) {
            order.setUniformSkuParams(source.getSkuRequests());
        }
        order.setOrderItems(uniformSkuService.buildOrderItems(order));
        order.setOrderTickets(uniformSkuService.buildOrderTickets(order));

    }

    private void setOrderDelivery(SystemCreateOrderParam source, Order order) {
        OrderDelivery delivery = new OrderDelivery();
        if (order.isTicket()) {
            FetchTicketWay fetchTicketWay = fetchTicketWayService.findOneByProgramIdAndType(order.getProductId(), source.getDeliverType());
            delivery.setDeliverType(source.getDeliverType().getCode());
            delivery.setNeedIdcard(fetchTicketWay.getNeedIdcard());
            delivery.setAddresseeName(source.getRecipientName());
            delivery.setAddresseeMobile(source.getRecipientMobile());
            delivery.setDeliveryStatus(DeliverStatusEnum.UNDELIVERED.getCode());
            delivery.setEnterpriseId(order.getEnterpriseId());
            if (DeliverTypeEnum.EXPRESS.getCode() == source.getDeliverType().getCode()) {
                delivery.setExpressFee(new Money(fetchTicketWay.getExpressFee()).getCent());
                order.setDeliveryFee(new Money(fetchTicketWay.getExpressFee()).getCent());
            } else if (DeliverTypeEnum.OFFLINE.getCode() == source.getDeliverType().getCode()) {
                delivery.setLocaleAddress(fetchTicketWay.getFetchAddress()
                        + (StringUtils.isNotBlank(fetchTicketWay.getFetchTimeDesc()) ? "(" + fetchTicketWay.getFetchTimeDesc() + ")" : ""));
                delivery.setLocaleContact(fetchTicketWay.getContactMobile());

            }
        } else if (order.isGoods()) {
            delivery.setDeliverType(source.getDeliverType().getCode());
            delivery.setNeedIdcard(CommonStateEnum.INVALID.getCode());
            delivery.setAddresseeName(source.getRecipientName());
            delivery.setAddresseeMobile(source.getRecipientMobile());
            delivery.setDeliveryStatus(DeliverStatusEnum.UNDELIVERED.getCode());
            delivery.setEnterpriseId(order.getEnterpriseId());
        }
        RecipientAddressParam recipientAddressParam = source.getRecipientAddressParam();
        if (recipientAddressParam != null) {
            delivery.setDetailedAddress(recipientAddressParam.getDetailedAddress());
            delivery.setProvinceName(recipientAddressParam.getProvinceName());
            delivery.setCityName(recipientAddressParam.getCityName());
            delivery.setDistrictName(recipientAddressParam.getDistrictName());
            if (StringUtils.isBlank(delivery.getAddresseeName())) {
                delivery.setAddresseeName(recipientAddressParam.getAddresseeName());
            }
            if (StringUtils.isBlank(delivery.getAddresseeMobile())) {
                delivery.setAddresseeMobile(recipientAddressParam.getAddresseeMobile());
            }
        }
        order.setDeliverType(delivery.getDeliverType());
        order.setDelivery(delivery);
    }


}
