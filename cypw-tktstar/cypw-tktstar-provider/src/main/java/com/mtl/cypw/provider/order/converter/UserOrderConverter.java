package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.common.utils.SnowflakeUtils;
import com.mtl.cypw.domain.order.enums.ConsumeTypeEnum;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.param.UserCreateOrderParam;
import com.mtl.cypw.member.pojo.MemberAddress;
import com.mtl.cypw.member.service.MemberAddressService;
import com.mtl.cypw.mpm.model.EnterpriseTemplate;
import com.mtl.cypw.mpm.service.EnterpriseTemplateService;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDelivery;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-02 15:54
 */
@Slf4j
@Component
public class UserOrderConverter extends DataConverter<UserCreateOrderParam, Order> {

    @Autowired
    private EnterpriseTemplateService enterpriseTemplateServiceImpl;

    @Autowired
    private FetchTicketWayService fetchTicketWayService;

    @Autowired
    private MemberAddressService memberAddressService;

    @Autowired
    private UniformSkuService uniformSkuService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private EventService eventService;

    @Override
    public Order convert(UserCreateOrderParam source) {
        Assert.notNull(source, "UserCreateOrderParam is null");
        Order order = new Order();
        Date currentDate = new Date();
        order.setOrderTitle(source.getOrderType().getName() + "订单");
        order.setOrderTime(currentDate);
        order.setCreateTime(currentDate);
        order.setOrderType(source.getOrderType().getCode());
        order.setMobileNo(source.getMobileNo());
        order.setMemberId(source.getMemberId());
        order.setClientIp(source.getClientIp());
        order.setWechatAppId(source.getWeChatAppId());
        order.setWechatOpenId(source.getWeChatOpenId());
        order.setEnterpriseId(source.getEnterpriseId());
        order.setChannelId(source.getChannel().getCode());
        order.setOrderStatus(OrderStatusEnum.INIT.getCode());
        order.setPlatformId(source.getSrc().getCode());
        if (source.getTotalPrice() != null) {
            order.setActualAmount(source.getTotalPrice().getCent());
        }
        if (source.getOrderAmount() != null) {
            order.setTicketAmount(source.getOrderAmount().getCent());
            order.setOriginAmount(source.getOrderAmount().getCent());
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
        setOrderDiscountDetails(source, order);

        // 订单明细信息
        setOrderItemsAndTickets(source, order);

        // 订单消耗库存记录
        setStockConsumeRecord(source, order);
        return order;
    }

    private void setOrderSnapshot(UserCreateOrderParam source, Order order) {
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

    private void setStockConsumeRecord(UserCreateOrderParam source, Order order) {
        OrderInventoryConsumeRecord consumeRecord = new OrderInventoryConsumeRecord();
        consumeRecord.setEnterpriseId(order.getEnterpriseId());
        consumeRecord.setMemberId(order.getMemberId());
        consumeRecord.setEventId(order.getEventId());
        if (order.isGoods()) {
            consumeRecord.setEventId(0);
            consumeRecord.setConsumeType(ConsumeTypeEnum.GOODS.getCode());
        } else if (order.isOnlySeat()) {
            consumeRecord.setConsumeType(ConsumeTypeEnum.SEAT_TICKET.getCode());
        } else {
            consumeRecord.setConsumeType(ConsumeTypeEnum.TICKET.getCode());
        }
        order.setInventoryConsumeRecord(consumeRecord);
    }

    private void setOrderDiscountDetails(UserCreateOrderParam source, Order order) {
        if (CollectionUtils.isEmpty(order.getOrderCouponParams())) {
            order.setOrderCouponParams(source.getOrderCouponParams());
        }
        order.setDiscountDetails(uniformSkuService.buildOrderDiscountDetails(order));
    }

    private void setOrderItemsAndTickets(UserCreateOrderParam source, Order order) {
        if (CollectionUtils.isEmpty(order.getUniformSkuParams())) {
            order.setUniformSkuParams(source.getSkuRequests());
        }
        order.setOrderItems(uniformSkuService.buildOrderItems(order));
        order.setOrderTickets(uniformSkuService.buildOrderTickets(order));

    }

    private void setOrderDelivery(UserCreateOrderParam source, Order order) {
        OrderDelivery delivery = new OrderDelivery();
        if (order.isTicket()) {
            FetchTicketWay fetchTicketWay = fetchTicketWayService.findOneByProgramIdAndType(order.getProductId(), source.getDeliverType());
            delivery.setDeliverType(source.getDeliverType().getCode());
            delivery.setNeedIdcard(fetchTicketWay.getNeedIdcard());
            delivery.setAddresseeIdcard(source.getRecipientIdNo());
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
            EnterpriseTemplate template = enterpriseTemplateServiceImpl.getEnterpriseTemplateByEnterpriseId(order.getEnterpriseId());
            delivery.setDeliverType(source.getDeliverType().getCode());
            delivery.setNeedIdcard(CommonStateEnum.INVALID.getCode());
            delivery.setAddresseeIdcard(source.getRecipientIdNo());
            delivery.setAddresseeName(source.getRecipientName());
            delivery.setAddresseeMobile(source.getRecipientMobile());
            delivery.setDeliveryStatus(DeliverStatusEnum.UNDELIVERED.getCode());
            delivery.setEnterpriseId(order.getEnterpriseId());
            if (DeliverTypeEnum.EXPRESS.getCode() == source.getDeliverType().getCode()
                    && order.getActualAmount() < template.gainDeliveryRestrictForCent()) {
                delivery.setExpressFee(template.gainDeliveryFeeForCent());
                order.setDeliveryFee(template.gainDeliveryFeeForCent());
            }
        }

        if (source.getRecipientAddressId() != null && source.getRecipientAddressId() > 0) {
            MemberAddress memberAddress = memberAddressService.getMemberAddress(source.getRecipientAddressId());
            delivery.setDetailedAddress(memberAddress.getDeliveryAddress());
            delivery.setProvinceName(memberAddress.getProvinceName());
            delivery.setCityName(memberAddress.getCityName());
            delivery.setDistrictName(memberAddress.getDistrictName());
            if (StringUtils.isBlank(delivery.getAddresseeName())) {
                delivery.setAddresseeName(memberAddress.getDeliveryName());
            }
            if (StringUtils.isBlank(delivery.getAddresseeMobile())) {
                delivery.setAddresseeMobile(memberAddress.getDeliveryMobile());
            }
        }
        order.setDeliverType(delivery.getDeliverType());
        order.setDelivery(delivery);
    }

}
