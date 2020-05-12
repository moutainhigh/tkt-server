package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.SnowflakeUtils;
import com.mtl.cypw.coupon.service.PromotionCouponService;
import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.order.exception.OrderBizException;
import com.mtl.cypw.order.exception.init.OrderBuyLimitException;
import com.mtl.cypw.order.exception.init.OrderInitException;
import com.mtl.cypw.order.exception.lock.OrderLockException;
import com.mtl.cypw.order.handler.OrderLockErrorHandler;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.service.OrderLimitService;
import com.mtl.cypw.order.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:55
 */
public abstract class AbstractUniformOrderService implements UniformOrderService {

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private OrderLimitService orderLimitServiceImpl;

    @Autowired
    private PromotionCouponService promotionCouponService;

    @Autowired
    private OrderLockErrorHandler orderLockErrorHandler;

    @Override
    public Order init(Order order, UniformCreateOrderParam request) {
        // 1. 下单校验
        verify(order, request);
        // 2. 生成订单编号
        if (StringUtils.isBlank(order.getOrderNo())) {
            String orderNo = SnowflakeUtils.getOrderNo();
            order.setOrderNo(orderNo);
        }
        // 3. 风控检查
        risk(order, request);
        // 4. 保存订单(预订单 status = 0, 前台不可见)
        order = orderServiceImpl.save(order);
        // 5. 消耗限购资源【特权购】
        order = consumeLimit(order);
        // 6. 预扣库存
        order = tryLockStock(order);
        // 7. 票证映射【实名制】
        order = ticketMapping(order);
        // 8. 摩天轮订单中心下单【中台】
        order = callMtlOrderCenter(order);
        return order;
    }

    @Override
    public Order lock(Order order) {
        if (Objects.isNull(order)) {
            throw new OrderBizException("订单错误");
        }
        try {
            // lock order
            order = orderServiceImpl.lock(order);
            // lock coupon
            if (CollectionUtils.isNotEmpty(order.getDiscountDetails())) {
                String orderNo = order.getOrderNo();
                Integer memberId = order.getMemberId();
                for (OrderDiscountDetail discountDetail : order.getDiscountDetails()) {
                    promotionCouponService.updateUsed(discountDetail.getCouponId(), memberId, orderNo);
                }
            }
            order = orderServiceImpl.orderInventoryAndStore(order);
            if (needStartTicketAfterLock()) {
                ticket(order);
            }
            return order;
        } catch (OrderLockException e) {
            orderLockErrorHandler.handle(order, e);
            throw e;
        }
    }

    @Override
    public Order ticket(Order order) {
        return orderServiceImpl.ticket(order);
    }


    /**
     * 票证映射
     * @param order
     * @return
     */
    private Order ticketMapping(Order order) {
        if (!needTicketMapping()) {
            return order;
        }
        return order;
    }

    /**
     * 推订单中台
     * @param order
     * @return
     */
    private Order callMtlOrderCenter(Order order) {
        if (!needCreateOrderForMtlCenter()) {
            return order;
        }
        return order;
    }

    /**
     * 限购校验
     * @param order
     * @return
     */
    private Order consumeLimit(Order order) {
        if (!needConsumeLimit()) {
            return order;
        }
        try {
            boolean consumable = orderLimitServiceImpl.consume(order);
            if (!consumable) {
                throw new OrderBuyLimitException(ErrorCode.ERROR_ORDER_INIT_BUY_LIMIT.getMsg());
            }
        } catch (OrderInitException e) {
            if (orderLimitServiceImpl.rollback(order)) {
                order.lockFailed();
                orderServiceImpl.save(order);
            }
            throw e;
        }
        return order;

    }

    /**
     * 预扣库存
     * @param order
     * @return
     */
    private Order tryLockStock(Order order) {
        try {
            if (order.isOnlySeat()) {
                order = orderServiceImpl.lockInventory(order);
            } else {
                order = orderServiceImpl.tryLockStock(order);
            }
        } catch (OrderLockException e) {
            orderLockErrorHandler.handle(order, e);
            throw e;
        }
        return order;
    }

    /**
     * 风控
     * @param order
     * @param request
     */
    protected abstract void risk(Order order, UniformCreateOrderParam request);

    /**
     * 订单校验
     * @param order
     * @param request
     */
    protected abstract void verify(Order order, UniformCreateOrderParam request);

    /**
     * 是否需要限购消费
     * @return
     */
    protected abstract boolean needConsumeLimit();

    /**
     * 是否需要票证映射
     * @return
     */
    protected abstract boolean needTicketMapping();

    /**
     * 是否先票后库存
     * @return
     */
    protected abstract boolean needStartTicketAfterLock();

    /**
     * 是否推送到摩天轮订单中心
     * @return
     */
    protected abstract boolean needCreateOrderForMtlCenter();

}
