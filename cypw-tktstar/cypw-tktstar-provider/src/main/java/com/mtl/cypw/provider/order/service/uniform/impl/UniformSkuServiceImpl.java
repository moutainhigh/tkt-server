package com.mtl.cypw.provider.order.service.uniform.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mtl.cypw.common.component.CypwApolloConfig;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.util.BigDecimalUtils;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.coupon.enums.PromotionTypeEnum;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import com.mtl.cypw.domain.order.param.OrderCouponParam;
import com.mtl.cypw.domain.order.param.UniformSeatParam;
import com.mtl.cypw.domain.order.param.UniformSeatTicketParam;
import com.mtl.cypw.domain.order.param.UniformSkuParam;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.mpm.model.Zone;
import com.mtl.cypw.order.exception.init.CouponMultiException;
import com.mtl.cypw.order.exception.init.OrderTicketNumberLimitException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.model.OrderItem;
import com.mtl.cypw.order.model.OrderTicket;
import com.mtl.cypw.provider.order.service.uniform.SkuService;
import com.mtl.cypw.provider.order.service.uniform.SkuServiceFactory;
import com.mtl.cypw.provider.order.service.uniform.SpecialService;
import com.mtl.cypw.provider.order.service.uniform.SpecialServiceFactory;
import com.mtl.cypw.provider.order.service.uniform.UniformSkuService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.pojo.EventSeat;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-03 10:58
 */
@Slf4j
@Service
public class UniformSkuServiceImpl implements UniformSkuService {

    @Resource
    private SkuServiceFactory skuServiceFactory;

    @Resource
    private SpecialServiceFactory specialServiceFactory;

    @Resource
    private EventPriceService eventPriceService;

    @Resource
    private EventService eventService;

    @Resource
    private SeatService seatService;

    @Resource
    private CypwApolloConfig cypwApolloConfig;

    @Override
    public List<OrderDiscountDetail> buildOrderDiscountDetails(Order order) {
        if (CollectionUtils.isEmpty(order.getOrderCouponParams())) {
            return Collections.emptyList();
        }
        Map<PromotionTypeEnum, List<OrderCouponParam>> promotionTypeGroupMap =
                order.getOrderCouponParams().stream().collect(Collectors.groupingBy(OrderCouponParam :: getCouponType));
        if (promotionTypeGroupMap.size() > 1) {
            log.error("不能同时参加多个优惠活动, promotionGroup {}", JsonUtils.toJson(promotionTypeGroupMap));
            throw new CouponMultiException("不能同时参加多个优惠活动");
        }
        for (Map.Entry<PromotionTypeEnum, List<OrderCouponParam>> entry : promotionTypeGroupMap.entrySet()) {
            if (PromotionTypeEnum.EXCHANGE_COUPON.getCode() == entry.getKey().getCode()) {
                continue;
            }
            List<OrderCouponParam> couponParams = entry.getValue();
            if (CollectionUtils.isNotEmpty(couponParams) && couponParams.size() > 1) {
                log.error("不能同时参加多个优惠活动， coupons {}", JsonUtils.toJson(couponParams));
                throw new CouponMultiException("不能同时参加多个优惠活动");
            }
        }
        List<OrderDiscountDetail> orderDiscountDetails = Lists.newArrayList();
        for (OrderCouponParam couponParam : order.getOrderCouponParams()) {
            SpecialService specialService = specialServiceFactory.selector(couponParam.getCouponType());
            OrderDiscountDetail discountDetail = specialService.buildDiscountDetail(order, couponParam);
            orderDiscountDetails.add(discountDetail);
        }
        return orderDiscountDetails;
    }

    @Override
    public List<OrderItem> buildOrderItems(Order order) {
        List<OrderItem> orderItems = Lists.newArrayList();
        if (order.isTicket() && CollectionUtils.isNotEmpty(order.getUniformSeatTicketParams())) {
            orderItems.addAll(buildOrderItemsForSeat(order));
        } else {
            for (UniformSkuParam sku : order.getUniformSkuParams()) {
                SkuService skuService = skuServiceFactory.selector(sku.getType());
                OrderItem orderItem = skuService.buildOrderItem(order, sku);
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }

    private List<OrderItem> buildOrderItemsForSeat(Order order) {
        List<OrderItem> items = Lists.newArrayList();
        Map<Integer, OrderItem> orderItemMap = Maps.newHashMap();
        Map<Integer, EventPrice> priceMap = Maps.newHashMap();
        for (UniformSeatTicketParam ticketParam : order.getUniformSeatTicketParams()) {
            EventPrice price = priceMap.get(ticketParam.getTicketId());
            if (price == null) {
                price = eventPriceService.getEventPriceById(ticketParam.getTicketId());
                Assert.isTrue(price != null, ErrorCode.ERROR_ORDER_INIT_TICKET_STATUS.getMsg());
                priceMap.put(price.getPriceId(), price);
            }
            OrderItem item = new OrderItem();
            item.setItemId(price.getEventId());
            item.setProductTitle(price.getPriceTitle());
            item.setEnterpriseId(order.getEnterpriseId());
            item.setIsPackage(0);
            item.setPackageNumber(1);
            item.setSkuType(SkuTypeEnum.SEAT_TICKET.getCode());
            item.setPriceId(price.getPriceId());
            item.setPriceDesc(price.getPriceTitle());
            int count = ticketParam.getSeats().size() / item.getPackageNumber();
            long unitPrice = new Money(price.getPriceValue()).getCent();
            item.setUnitPrice(unitPrice);
            item.setOriginPrice(unitPrice);
            item.setQuantity(count);
            item.setCostFee(count * unitPrice);
            item.setDiscountFee(0L);
            items.add(item);
            orderItemMap.put(price.getPriceId(), item);
        }
        return items;
    }


    @Override
    public List<OrderTicket> buildOrderTickets(Order order) {
        int buyCount = 0;
        for (OrderItem item : order.getOrderItems()) {
            buyCount += item.getQuantity() * item.getPackageNumber();
        }
        if (buyCount > cypwApolloConfig.getBuyLimitQuantity()) {
            log.error("超过订单购买限制数， count {}", buyCount);
            throw new OrderTicketNumberLimitException("您的购买数量太多了~");
        }
        if (CollectionUtils.isNotEmpty(order.getSeatIdSet())) {
            return buildTicketsForSeat(order);
        }
        return buildTickets(order);
    }

    private List<OrderTicket> buildTicketsForSeat(Order order) {
        BigDecimal totalAmountCent = BigDecimal.ZERO;
        BigDecimal totalDiscountFeeCent = BigDecimal.ZERO;
        List<OrderItem> orderItems = order.getOrderItems();
        Assert.notEmpty(orderItems, "orderItems is empty.");
        List<OrderTicket> orderTickets = Lists.newArrayList();
        Map<Integer, OrderItem> orderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem :: getPriceId, e -> e));
        Map<Integer, List<OrderDiscountDetail>> promotionTypeGroupMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(order.getDiscountDetails())) {
            promotionTypeGroupMap = order.getDiscountDetails().stream().collect(Collectors.groupingBy(OrderDiscountDetail :: getDiscountType));
        }
        List<OrderDiscountDetail> exchangeCodeList = promotionTypeGroupMap.get(PromotionTypeEnum.EXCHANGE_COUPON.getCode());
        Map<Integer, List<OrderDiscountDetail>> exchangeCodeGroupMap = Maps.newHashMap();
        BigDecimal discountRate = getDiscountRate(order.getDiscountDetails(), BigDecimal.valueOf(order.getTicketAmount()));
        if (CollectionUtils.isNotEmpty(exchangeCodeList)) {
            exchangeCodeGroupMap = exchangeCodeList.stream().collect(Collectors.groupingBy(OrderDiscountDetail :: getExchangeSkuId));
        }
        SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
        seatQuerySpec.setEventId(order.getEventId());
        seatQuerySpec.setSeatIds(Lists.newArrayList(order.getSeatIdSet()));
        List<EventSeat> seatList = seatService.findBySpec(seatQuerySpec);
        Map<Integer, EventSeat> seatMap = seatList.stream().collect(Collectors.toMap(EventSeat :: getSeatId, e -> e));
        List<Zone> zones = eventService.findTemplateZonesByEventId(order.getEventId());
        Map<Integer, Zone> zoneMap = zones.stream().collect(Collectors.toMap(Zone :: getZoneId, e -> e));
        int batchNo = 1;
        for (UniformSeatTicketParam seatTicketParam : order.getUniformSeatTicketParams()) {
            OrderItem orderItem = orderItemMap.get(seatTicketParam.getTicketId());
            int packageNum = orderItem.getPackageNumber();
            int quantity = orderItem.getQuantity();
            int count = packageNum * quantity;
            int exchangeIndex = 1;
            List<OrderDiscountDetail> exchangeDiscountDetails = exchangeCodeGroupMap.get(orderItem.getPriceId());
            BigDecimal discountFeeCent = BigDecimal.ZERO;
            BigDecimal unitPrice = BigDecimal.valueOf(orderItem.getUnitPrice());
            BigDecimal averagePrice = buildAveragePrice(quantity, count, discountRate, unitPrice);
            ArrayListMultimap<Integer, UniformSeatParam> packageMap = ArrayListMultimap.create();
            int step = 0;
            for (UniformSeatParam seatParam : seatTicketParam.getSeats()) {
                packageMap.put(batchNo, seatParam);
                if (++step % packageNum == 0) {
                    batchNo++;
                    step = 0;
                }
            }
            Set<Integer> seatIds = Sets.newHashSet();
            for (Integer thePackage : packageMap.keySet()) {
                List<UniformSeatParam> seatParams = packageMap.get(thePackage);
                for (UniformSeatParam seat : seatParams) {
                    OrderTicket orderTicket = new OrderTicket();
                    orderTicket.setPriceId(orderItem.getPriceId());
                    EventSeat base = seatMap.get(seat.getSeatId());
                    if (base == null) {
                        log.error("选座购票座位错误, seatId[{}], seatName[{}]", seat.getSeatId(), seat.getSeatName());
                        throw new OrderTicketNumberLimitException("选座购票座位错误");
                    }
                    orderTicket.setSeatId(seat.getSeatId());
                    orderTicket.setSeatName(seat.getSeatName());
                    orderTicket.setZoneId(base.getZoneId());
                    orderTicket.setZoneName(zoneMap.get(base.getZoneId()).getZoneName());
                    if (CollectionUtils.isNotEmpty(exchangeDiscountDetails) && exchangeIndex <= exchangeDiscountDetails.size()) {
                        Optional<OrderDiscountDetail> optional = Optional.ofNullable(exchangeDiscountDetails.get(exchangeIndex - 1));
                        if (optional.isPresent()) {
                            orderTicket.setCouponId(optional.get().getCouponId());
                            orderTicket.setAveragePrice(orderItem.getUnitPrice());
                            discountFeeCent = discountFeeCent.add(BigDecimal.valueOf(optional.get().getDiscountAmount()));
                            exchangeIndex++;
                        }
                    } else {
                        // TODO: 套票处理
                        orderTicket.setAveragePrice(BigDecimalUtils.rounded2Long(averagePrice));
                        discountFeeCent = discountFeeCent.add(unitPrice.subtract(averagePrice));
                    }
                    orderTicket.setPackageNumber(packageNum);
                    orderTicket.setTicketPrice(orderItem.getUnitPrice());
                    orderTicket.setOriginPrice(orderItem.getOriginPrice());
                    orderTicket.setTicketDesc(orderItem.getPriceDesc());
                    orderTicket.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
                    orderTicket.setEnterpriseId(orderItem.getEnterpriseId());
                    orderTickets.add(orderTicket);
                    seatIds.add(seat.getSeatId());
                }
            }
            if (seatIds.size() < seatTicketParam.getSeats().size()) {
                log.error("选座购票座位重复, ticketSeatIds[{}], buySeats[{}]", JsonUtils.toJson(seatIds), JsonUtils.toJson(seatTicketParam.getSeats()));
                throw new OrderTicketNumberLimitException("选座购票座位重复");
            }
            BigDecimal itemAmountCent = unitPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            orderItem.setCostFee(BigDecimalUtils.rounded2Long(itemAmountCent.subtract(discountFeeCent)));
            orderItem.setDiscountFee(BigDecimalUtils.rounded2Long(discountFeeCent));
            totalAmountCent = totalAmountCent.add(itemAmountCent);
            totalDiscountFeeCent = totalDiscountFeeCent.add(discountFeeCent);
        }
        order.setSettleAmount(BigDecimalUtils.rounded2Long(totalAmountCent));
        order.setTicketAmount(BigDecimalUtils.rounded2Long(totalAmountCent));
        order.setDiscountFee(BigDecimalUtils.rounded2Long(totalDiscountFeeCent));
        BigDecimal actualAmount = totalAmountCent.subtract(totalDiscountFeeCent);
        if (order.getDeliveryFee() != null) {
            order.setActualAmount(BigDecimalUtils.rounded2Long(actualAmount.add(BigDecimal.valueOf(order.getDeliveryFee()))));
        } else {
            order.setActualAmount(BigDecimalUtils.rounded2Long(actualAmount));
        }
        return orderTickets;
    }

    private List<OrderTicket> buildTickets(Order order) {
        BigDecimal totalAmountCent = BigDecimal.ZERO;
        BigDecimal totalDiscountFeeCent = BigDecimal.ZERO;
        List<OrderItem> orderItems = order.getOrderItems();
        Assert.notEmpty(orderItems, "orderItems is empty.");
        List<OrderTicket> orderTickets = Lists.newArrayList();
        Map<Integer, OrderItem> orderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem ::getPriceId, e -> e));
        Map<Integer, List<OrderDiscountDetail>> promotionTypeGroupMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(order.getDiscountDetails())) {
            promotionTypeGroupMap = order.getDiscountDetails().stream().collect(Collectors.groupingBy(OrderDiscountDetail :: getDiscountType));
        }
        List<OrderDiscountDetail> exchangeCodeList = promotionTypeGroupMap.get(PromotionTypeEnum.EXCHANGE_COUPON.getCode());
        Map<Integer, List<OrderDiscountDetail>> exchangeCodeGroupMap = Maps.newHashMap();
        BigDecimal discountRate = getDiscountRate(order.getDiscountDetails(), BigDecimal.valueOf(order.getTicketAmount()));
        if (CollectionUtils.isNotEmpty(exchangeCodeList)) {
            exchangeCodeGroupMap = exchangeCodeList.stream().collect(Collectors.groupingBy(OrderDiscountDetail :: getExchangeSkuId));
        }
        int batchNo = 1;
        for (Map.Entry<Integer, OrderItem> entry: orderItemMap.entrySet()) {
            Integer skuId = entry.getKey();
            OrderItem orderItem = entry.getValue();
            int exchangeIndex = 1;
            List<OrderDiscountDetail> exchangeDiscountDetails = exchangeCodeGroupMap.get(skuId);
            BigDecimal discountFeeCent = BigDecimal.ZERO;
            BigDecimal unitPrice = BigDecimal.valueOf(orderItem.getUnitPrice());
            int packageNum = orderItem.getPackageNumber();
            int quantity = orderItem.getQuantity();
            int count = packageNum * quantity;
            BigDecimal averageDiscountPrice = buildAveragePrice(quantity, count, discountRate, unitPrice);
            if (orderItem.isTicket()) {
                ArrayListMultimap<Integer, Integer> packageMap = ArrayListMultimap.create();
                int step = 0;
                for (int i = 0; i < count; i++) {
                    packageMap.put(batchNo, 1);
                    if (++step % packageNum == 0) {
                        batchNo++;
                        step = 0;
                    }
                }
                for (Integer thePackage : packageMap.keySet()) {
                    List<Integer> tickets = packageMap.get(thePackage);
                    for (int i = 0; i < tickets.size(); i++) {
                        OrderTicket orderTicket = new OrderTicket();
                        orderTicket.setPriceId(skuId);
                        if (CollectionUtils.isNotEmpty(exchangeDiscountDetails) && exchangeIndex <= exchangeDiscountDetails.size()) {
                            Optional<OrderDiscountDetail> optional = Optional.ofNullable(exchangeDiscountDetails.get(exchangeIndex - 1));
                            if (optional.isPresent()) {
                                orderTicket.setCouponId(optional.get().getCouponId());
                                orderTicket.setAveragePrice(orderItem.getUnitPrice());
                                discountFeeCent = discountFeeCent.add(BigDecimal.valueOf(optional.get().getDiscountAmount()));
                                exchangeIndex++;
                            }
                        } else {
                            // TODO: 套票处理
                            orderTicket.setAveragePrice(BigDecimalUtils.rounded2Long(averageDiscountPrice));
                            discountFeeCent = discountFeeCent.add(unitPrice.subtract(averageDiscountPrice));
                        }
                        orderTicket.setPackageNumber(packageNum);
                        orderTicket.setTicketPrice(orderItem.getUnitPrice());
                        orderTicket.setOriginPrice(orderItem.getOriginPrice());
                        orderTicket.setTicketDesc(orderItem.getPriceDesc());
                        orderTicket.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
                        orderTicket.setEnterpriseId(orderItem.getEnterpriseId());
                        orderTickets.add(orderTicket);
                    }
                }

            } else if (order.isGoods()) {
                for (int i = 0; i < count; i++) {
                    OrderTicket orderTicket = new OrderTicket();
                    orderTicket.setPriceId(skuId);
                    if (CollectionUtils.isNotEmpty(exchangeDiscountDetails) && exchangeIndex <= exchangeDiscountDetails.size()) {
                        Optional<OrderDiscountDetail> optional = Optional.ofNullable(exchangeDiscountDetails.get(exchangeIndex - 1));
                        if (optional.isPresent()) {
                            orderTicket.setCouponId(optional.get().getCouponId());
                            orderTicket.setAveragePrice(orderItem.getUnitPrice());
                            discountFeeCent = discountFeeCent.add(BigDecimal.valueOf(optional.get().getDiscountAmount()));
                            exchangeIndex++;
                        }
                    } else {
                        orderTicket.setAveragePrice(BigDecimalUtils.rounded2Long(averageDiscountPrice));
                        discountFeeCent = discountFeeCent.add(unitPrice.subtract(averageDiscountPrice));
                    }
                    orderTicket.setPackageNumber(packageNum);
                    orderTicket.setTicketPrice(orderItem.getUnitPrice());
                    orderTicket.setOriginPrice(orderItem.getOriginPrice());
                    orderTicket.setTicketDesc(orderItem.getPriceDesc());
                    orderTicket.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
                    orderTicket.setEnterpriseId(orderItem.getEnterpriseId());
                    orderTickets.add(orderTicket);
                }
            }
            BigDecimal itemAmountCent = unitPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            orderItem.setCostFee(BigDecimalUtils.rounded2Long(itemAmountCent.subtract(discountFeeCent)));
            orderItem.setDiscountFee(BigDecimalUtils.rounded2Long(discountFeeCent));
            totalAmountCent = totalAmountCent.add(itemAmountCent);
            totalDiscountFeeCent = totalDiscountFeeCent.add(discountFeeCent);

        }
        order.setSettleAmount(BigDecimalUtils.rounded2Long(totalAmountCent));
        order.setTicketAmount(BigDecimalUtils.rounded2Long(totalAmountCent));
        order.setDiscountFee(BigDecimalUtils.rounded2Long(totalDiscountFeeCent));
        BigDecimal actualAmount = totalAmountCent.subtract(totalDiscountFeeCent);
        if (order.getDeliveryFee() != null) {
            order.setActualAmount(BigDecimalUtils.rounded2Long(actualAmount.add(BigDecimal.valueOf(order.getDeliveryFee()))));
        } else {
            order.setActualAmount(BigDecimalUtils.rounded2Long(actualAmount));
        }
        return orderTickets;
    }

    private BigDecimal buildAveragePrice(int quantity, int count, BigDecimal discountRate, BigDecimal unitPrice) {
        BigDecimal totalSellPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return totalSellPrice.divide(BigDecimal.valueOf(count), 6, RoundingMode.HALF_UP).multiply(discountRate);
    }

    private BigDecimal getDiscountRate(List<OrderDiscountDetail> discountDetails, BigDecimal ticketAmount) {
        BigDecimal discountTotalAmount = BigDecimal.ZERO;
        for (OrderDiscountDetail discountDetail : discountDetails) {
            if ( PromotionTypeEnum.EXCHANGE_COUPON.getCode() == discountDetail.getDiscountType()) {
                continue;
            }
            discountTotalAmount = discountTotalAmount.add(BigDecimal.valueOf(discountDetail.getDiscountAmount()));
        }
        BigDecimal discountAmount = ticketAmount.compareTo(discountTotalAmount) <= 0 ? ticketAmount : discountTotalAmount;
        return ticketAmount.subtract(discountAmount).divide(ticketAmount, 9, RoundingMode.HALF_UP);
    }

}
