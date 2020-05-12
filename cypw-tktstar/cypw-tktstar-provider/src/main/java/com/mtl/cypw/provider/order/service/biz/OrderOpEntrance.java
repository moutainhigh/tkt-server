package com.mtl.cypw.provider.order.service.biz;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.param.*;
import com.mtl.cypw.order.exception.OrderBizException;
import com.mtl.cypw.order.exception.cancel.OrderCancelException;
import com.mtl.cypw.order.exception.refund.OrderRefundException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.order.service.OrderService;
import com.mtl.cypw.order.service.OrderSmsEntrance;
import com.mtl.cypw.payment.service.PaymentLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-17 14:07
 */
@Slf4j
@Component
public class OrderOpEntrance {

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private OrderSmsEntrance orderSmsEntrance;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Autowired
    private PaymentLogService paymentLogService;

    /**
     * 订单支付完成
     * @param request
     * @return
     */
    public TSingleResult<Boolean> paidOrder(OrderPaidParam request) {
        log.info("pay success confirm, request[{}]", JsonUtils.toJson(request));
        Order order = orderQueryServiceImpl.findOneById(request.getOrderId());
        if (order == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_ORDER.getCode(), ErrorCode.ERROR_ORDER.getMsg());
        }
        if (!order.isLocked()) {
            log.warn("Current order's status already changed, no need update status, orderId:{}, current:{}",
                    request.getOrderId(), OrderStatusEnum.getObject(order.getOrderStatus()).getName());
            return ResultBuilder.succTSingle(Boolean.TRUE);
        }
        order.setActualAmount(request.getOrderPrice().getCent());
        order.setTransactionFlowNo(request.getSerialNo());
        order.setPaymentTypeId(request.getPayType().getPayCode());
        order.paySuccess(request.getPaySuccessTime() == null ? DateUtils.now() : request.getPaySuccessTime());
        orderServiceImpl.paid(order);
        if (order.isPaid()) {
            // 订单支付成功短信
            orderSmsEntrance.sendPaymentSuccessMessage(order);
            // 配货/票，异步改造
            if (order.isWaitToTicket()) {
                ThreadPoolUtil.execute(() -> {
                    log.info("order ticket, orderNo [{}], datetime [{}]", order.getOrderNo(),
                            DateUtils.format(DateUtils.now(), DateUtils.millisecondFormat));
                    orderServiceImpl.ticket(order);
                });
            }
        }
        //修改支付日志状态
        paymentLogService.updateStatus(order.getId());
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }

    /**
     * 订单取消
     * @param request
     * @return
     */
    public TSingleResult<Boolean> cancelOrder(OrderCancelParam request) {
        log.info("cancel order, request[{}]", JsonUtils.toJson(request));
        try {
            orderServiceImpl.cancel(request);
        } catch (OrderCancelException cancelExc) {
            return ResultBuilder.failTSingle(cancelExc.getCode(), cancelExc.getMessage());
        } catch (Exception e) {
            log.error("cancel order exception, request[{}]", JsonUtils.toJson(request), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_ORDER.getCode(), ErrorCode.ERROR_ORDER.getMsg());
        }
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }

    /**
     * 订单退款
     * @param request
     * @return
     */
    public TSingleResult<Boolean> refundOrder(OrderRefundParam request) {
        log.info("refund order, request[{}]", JsonUtils.toJson(request));
        try {
            orderServiceImpl.refund(request);
        } catch (OrderRefundException refundExc) {
            return ResultBuilder.failTSingle(refundExc.getCode(), refundExc.getMessage());
        } catch (Exception e) {
            log.error("refund order exception, request[{}]", JsonUtils.toJson(request), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_ORDER.getCode(), ErrorCode.ERROR_ORDER.getMsg());
        }
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }

    /**
     * 订单配票
     * @param request
     * @return
     */
    public TSingleResult<Boolean> ticketOrder(OrderTicketParam request) {
        log.info("ticket order, request[{}]", JsonUtils.toJson(request));
        Order order = orderQueryServiceImpl.findOneById(request.getOrderId());
        if (order == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_ORDER.getCode(), ErrorCode.ERROR_ORDER.getMsg());
        }
        if (order.isTicketing() || order.isWaitToTicket()) {
            orderServiceImpl.ticket(order);
        }
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }

    /**
     * 订单隐藏(C端不可见)
     * @param request
     * @return
     */
    public TSingleResult<Boolean> deleteOrder(OrderRefundParam request) {
        return null;
    }

    /**
     * 确认收货
     * @param orderId 订单Id
     * @return boolean
     */
    public TSingleResult<Boolean> confirmReceipt(int orderId) {
        try {
            orderServiceImpl.confirmReceipt(orderId);
        } catch (OrderBizException confirmExc) {
            return ResultBuilder.failTSingle(confirmExc.getCode(), confirmExc.getMessage());
        }
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }

    /**
     * 绑定物流单号
     * @param request 绑定参数
     * @return TSingleResult<Boolean>
     */
    public TSingleResult<Boolean> bindExpressNo(BindExpressNoParam request) {
        log.info("bind order, request[{}]", JsonUtils.toJson(request));
        if (null == request || StringUtils.isEmpty(request.getExpressNo()) || StringUtils.isEmpty(request.getOrderNo())) {
            log.error("参数错误，订单号或快递单号为空");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        try {
            Order order = orderServiceImpl.bindExpressNo(request);
            //绑定物流号可能需要走自己的短息模板
            orderSmsEntrance.sendBindExpressNoSuccessMessage(order);
        } catch (OrderBizException confirmExc) {
            return ResultBuilder.failTSingle(confirmExc.getCode(), confirmExc.getMessage());
        }
        return ResultBuilder.succTSingle(Boolean.TRUE);
    }
}
