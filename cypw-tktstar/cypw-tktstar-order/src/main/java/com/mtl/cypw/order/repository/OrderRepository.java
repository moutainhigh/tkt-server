package com.mtl.cypw.order.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.common.util.CollatorUtils;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.mapper.*;
import com.mtl.cypw.order.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 12:29
 */
@Slf4j
@Component
public class OrderRepository implements GenericRepository<Integer, Order> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderTicketMapper orderTicketMapper;

    @Autowired
    private OrderRefundMapper orderRefundMapper;

    @Autowired
    private OrderSnapshotMapper orderSnapshotMapper;

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderDiscountDetailMapper orderDiscountDetailMapper;

    @Autowired
    private OrderGiftRecordMapper orderGiftRecordMapper;

    @Autowired
    private OrderSystemRecordMapper orderSystemRecordMapper;

    @Override
    public Integer save(Order order) {
        return this.store(order).getId();
    }

    public Order store(Order order) {
        Assert.notNull(order, "order is empty.");
        if (order.brandNew()) {
            order.setOrderStatus(OrderStatusEnum.INIT.getCode());
            orderMapper.insertSelective(order);
            if (order.getOrderSnapshot() != null) {
                order.getOrderSnapshot().setOrderId(order.getId());
                orderSnapshotMapper.insertSelective(order.getOrderSnapshot());
            }
        } else {
            orderMapper.updateByPrimaryKeySelective(order);
        }

        OrderDelivery delivery = order.getDelivery();
        if (delivery != null) {
            delivery.setOrderId(order.getId());
            order.setDelivery(storeOrderDelivery(delivery));
        }

        OrderRefund refund = order.getRefund();
        if (refund != null) {
            refund.setOrderId(order.getId());
            order.setRefund(storeOrderRefund(refund));
        }

        if (CollectionUtils.isNotEmpty(order.getOrderItems())) {
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrderId(order.getId());
                storeOrderItem(orderItem);
            }
        }

        if (CollectionUtils.isNotEmpty(order.getOrderTickets())) {
            Map<Integer, OrderItem> orderItemMap = order.getOrderItems().stream().collect(
                    Collectors.toMap(OrderItem::getPriceId, e -> e));
            for (OrderTicket orderTicket : order.getOrderTickets()) {
                OrderItem orderItem = orderItemMap.get(orderTicket.getPriceId());
                orderTicket.setOrderId(order.getId());
                orderTicket.setOrderItemId(orderItem.getId());
                storeOrderTicket(orderTicket);
            }
        }

        if (CollectionUtils.isNotEmpty(order.getDiscountDetails())) {
            for (OrderDiscountDetail discountDetail : order.getDiscountDetails()) {
                discountDetail.setOrderId(order.getId());
                storeOrderDiscountDetail(discountDetail);
            }
        }

        // 线下赠票记录
        OrderGiftRecord giftRecord = order.getOrderGiftRecord();
        if (giftRecord != null) {
            giftRecord.setOrderId(order.getId());
            order.setOrderGiftRecord(storeOrderGiftRecord(giftRecord));
        }

        // 线下出票记录
        OrderSystemRecord systemRecord = order.getOrderSystemRecord();
        if (systemRecord != null) {
            systemRecord.setOrderId(order.getId());
            order.setOrderSystemRecord(storeOrderSystemRecord(systemRecord));
        }
        // 订单操作记录

        return order;
    }

    private OrderSystemRecord storeOrderSystemRecord(OrderSystemRecord systemRecord) {
        Assert.notNull(systemRecord, "systemRecord is null");
        Assert.isTrue(systemRecord.getOrderId() > 0, "order is error");
        if (systemRecord.brandNew()) {
            orderSystemRecordMapper.insertSelective(systemRecord);
        } else {
            orderSystemRecordMapper.updateByPrimaryKeySelective(systemRecord);
        }
        return systemRecord;
    }

    private OrderGiftRecord storeOrderGiftRecord(OrderGiftRecord giftRecord) {
        Assert.notNull(giftRecord, "giftRecord is null");
        Assert.isTrue(giftRecord.getOrderId() > 0, "order is error");
        if (giftRecord.brandNew()) {
            orderGiftRecordMapper.insertSelective(giftRecord);
        } else {
            orderGiftRecordMapper.updateByPrimaryKeySelective(giftRecord);
        }
        return giftRecord;
    }

    public OrderDelivery storeOrderDelivery(OrderDelivery delivery) {
        Assert.notNull(delivery, "delivery is null");
        Assert.isTrue(delivery.getOrderId() > 0, "order is error");
        if (delivery.brandNew()) {
            orderDeliveryMapper.insertSelective(delivery);
        } else {
            orderDeliveryMapper.updateByPrimaryKeySelective(delivery);
        }
        return delivery;
    }

    public OrderRefund storeOrderRefund(OrderRefund refund) {
        Assert.notNull(refund, "refund is null");
        Assert.isTrue(refund.getOrderId() > 0, "order is error");
        if (refund.brandNew()) {
            orderRefundMapper.insertSelective(refund);
        } else {
            orderRefundMapper.updateByPrimaryKeySelective(refund);
        }
        return refund;
    }

    private OrderItem storeOrderItem(OrderItem orderItem) {
        Assert.notNull(orderItem, "orderItem is null");
        Assert.isTrue(orderItem.getOrderId() > 0, "order is error");
        if (orderItem.brandNew()) {
            orderItemMapper.insertSelective(orderItem);
        } else {
            orderItemMapper.updateByPrimaryKeySelective(orderItem);
        }
        return orderItem;
    }

    private OrderTicket storeOrderTicket(OrderTicket orderTicket) {
        Assert.notNull(orderTicket, "orderTicket is null");
        Assert.isTrue(orderTicket.getOrderId() > 0, "order is error");
        if (orderTicket.brandNew()) {
            orderTicketMapper.insertSelective(orderTicket);
        } else {
            orderTicketMapper.updateByPrimaryKeySelective(orderTicket);
        }
        return orderTicket;
    }

    private OrderDiscountDetail storeOrderDiscountDetail(OrderDiscountDetail discountDetail) {
        Assert.notNull(discountDetail, "discountDetail is null");
        Assert.isTrue(discountDetail.getOrderId() > 0, "order is error");
        if (discountDetail.brandNew()) {
            orderDiscountDetailMapper.insertSelective(discountDetail);
        } else {
            orderDiscountDetailMapper.updateByPrimaryKeySelective(discountDetail);
        }
        return discountDetail;
    }

    public Order findOneById(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderQueryParam param = OrderQueryParam.builder()
                .isIncludingSnapshot(true)
                .isIncludingOrderDelivery(true)
                .isIncludingOrderRefund(true)
                .isIncludingOrderItems(true)
                .isIncludingOrderTickets(true)
                .isIncludingDiscountDetails(true)
                .isIncludingOrderOperationLog(true)
                .build();
        assignOrderDetail(order, param);
        return order;
    }

    public Order findOneByOrderNo(String orderNo) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo", orderNo);
        Order order = orderMapper.selectOneByExample(example);
        OrderQueryParam param = OrderQueryParam.builder()
                .isIncludingSnapshot(true)
                .isIncludingOrderDelivery(true)
                .isIncludingOrderRefund(true)
                .isIncludingOrderItems(true)
                .isIncludingOrderTickets(true)
                .isIncludingDiscountDetails(true)
                .isIncludingOrderOperationLog(true)
                .build();
        assignOrderDetail(order, param);
        return order;
    }

    public Order findOneByFetchCode(String fetchCode, Integer enterpriseId) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fetchCode", fetchCode);
        criteria.andEqualTo("enterpriseId", enterpriseId);
        Order order = orderMapper.selectOneByExample(example);
        OrderQueryParam param = OrderQueryParam.builder()
                .isIncludingSnapshot(true)
                .isIncludingOrderDelivery(true)
                .isIncludingOrderRefund(true)
                .isIncludingOrderItems(true)
                .isIncludingOrderTickets(true)
                .build();
        assignOrderDetail(order, param);
        return order;
    }

    public List<Order> pageQuery(OrderQueryParam param, Pagination pagination) {
        Example example = new Example(Order.class);
        this.setCriteria(param, example);
        CollatorUtils.parseSortByExample(pagination, example);
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        List<Order> orders = orderMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(orders)) {
            orders.stream().forEach(order -> assignOrderDetail(order, param));
        }
        pagination.setCount(page.getTotal());
        return orders;
    }

    public Integer countQuery(OrderQueryParam param) {
        Example example = new Example(Order.class);
        this.setCriteria(param, example);
        int count = orderMapper.selectCountByExample(example);
        return count;
    }

    public List<OrderDiscountDetail> findDiscountDetailsByOrderId(Integer orderId) {
        Example discountExample = new Example(OrderDiscountDetail.class);
        Example.Criteria discountExampleCriteria = discountExample.createCriteria();
        discountExampleCriteria.andEqualTo("orderId", orderId);
        List<OrderDiscountDetail> discountDetails = orderDiscountDetailMapper.selectByExample(discountExample);
        return discountDetails;
    }


    private void setCriteria(OrderQueryParam param, Example example) {
        if (param == null) {
            return;
        }
        Example.Criteria criteria = example.createCriteria();
        if (param.getOrderId() != null) {
            criteria.andEqualTo("id", param.getOrderId());
        }
        if (StringUtils.isNotBlank(param.getOrderNo())) {
            criteria.andEqualTo("orderNo", param.getOrderNo());
        }
        if (param.getMemberId() != null) {
            criteria.andEqualTo("memberId", param.getMemberId());
        }
        if (param.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        }
        if (param.getProductId() != null) {
            criteria.andEqualTo("productId", param.getProductId());
        }
        if (param.getOrderStatus() != null) {
            criteria.andEqualTo("orderStatus", param.getOrderStatus());
        }
        if (CollectionUtils.isNotEmpty(param.getOrderStatusList())) {
            criteria.andIn("orderStatus", param.getOrderStatusList());
        }

    }

    private void assignOrderDetail(Order order, OrderQueryParam param) {
        if (order == null || param == null) {
            return;
        }
        if (param.isIncludingSnapshot()) {
            Example snapshotExample = new Example(OrderSnapshot.class);
            Example.Criteria snapshotExampleCriteria = snapshotExample.createCriteria();
            snapshotExampleCriteria.andEqualTo("orderId", order.getId());
            OrderSnapshot snapshot = orderSnapshotMapper.selectOneByExample(snapshotExample);
            order.setOrderSnapshot(snapshot);
        }

        if (param.isIncludingOrderDelivery()) {
            Example deliveryExample = new Example(OrderDelivery.class);
            Example.Criteria deliveryExampleCriteria = deliveryExample.createCriteria();
            deliveryExampleCriteria.andEqualTo("orderId", order.getId());
            OrderDelivery delivery = orderDeliveryMapper.selectOneByExample(deliveryExample);
            order.setDelivery(delivery);
        }

        if (param.isIncludingOrderItems()) {
            Example itemExample = new Example(OrderItem.class);
            Example.Criteria itemExampleCriteria = itemExample.createCriteria();
            itemExampleCriteria.andEqualTo("orderId", order.getId());
            List<OrderItem> items = orderItemMapper.selectByExample(itemExample);
            order.setOrderItems(items);
        }

        if (param.isIncludingOrderTickets()) {
            Example ticketExample = new Example(OrderTicket.class);
            Example.Criteria ticketExampleCriteria = ticketExample.createCriteria();
            ticketExampleCriteria.andEqualTo("orderId", order.getId());
            List<OrderTicket> tickets = orderTicketMapper.selectByExample(ticketExample);
            order.setOrderTickets(tickets);
        }

        if (param.isIncludingDiscountDetails()) {
            List<OrderDiscountDetail> discountDetails = this.findDiscountDetailsByOrderId(order.getId());
            order.setDiscountDetails(discountDetails);
        }

        if (param.isIncludingOrderRefund()) {
            Example refundExample = new Example(OrderRefund.class);
            Example.Criteria refundExampleCriteria = refundExample.createCriteria();
            refundExampleCriteria.andEqualTo("orderId", order.getId());
            OrderRefund refund = orderRefundMapper.selectOneByExample(refundExample);
            order.setRefund(refund);
        }

    }
}
