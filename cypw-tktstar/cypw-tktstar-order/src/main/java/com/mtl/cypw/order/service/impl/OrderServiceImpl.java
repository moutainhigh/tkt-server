package com.mtl.cypw.order.service.impl;

import com.google.common.collect.Lists;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.component.CypwApolloConfig;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.ValidationException;
import com.mtl.cypw.common.util.ThreadPoolUtil;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.coupon.service.PromotionCouponService;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import com.mtl.cypw.domain.order.enums.CodeStatusEnum;
import com.mtl.cypw.domain.order.enums.CodeTypeEnum;
import com.mtl.cypw.domain.order.enums.ConsumeStatusEnum;
import com.mtl.cypw.domain.order.enums.ConsumeTypeEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderCancelEnum;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.order.enums.*;
import com.mtl.cypw.domain.order.param.BindExpressNoParam;
import com.mtl.cypw.domain.order.param.OrderCancelParam;
import com.mtl.cypw.domain.order.param.OrderRefundParam;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import com.mtl.cypw.domain.stock.param.SeatLockParam;
import com.mtl.cypw.domain.stock.param.SeatReleaseParam;
import com.mtl.cypw.domain.stock.param.StockRollbackWithRecordParam;
import com.mtl.cypw.order.assemble.OrderBuilder;
import com.mtl.cypw.order.exception.OrderBizException;
import com.mtl.cypw.order.exception.cancel.AlreadyCancelledException;
import com.mtl.cypw.order.exception.cancel.CancelRollbackCouponException;
import com.mtl.cypw.order.exception.cancel.CancelRollbackInventoryException;
import com.mtl.cypw.order.exception.cancel.NotOwnedException;
import com.mtl.cypw.order.exception.cancel.OrderCancelException;
import com.mtl.cypw.order.exception.cancel.UnableToCancelException;
import com.mtl.cypw.order.exception.lock.LockInventoryException;
import com.mtl.cypw.order.exception.lock.OrderLockException;
import com.mtl.cypw.order.exception.payment.OrderPaidException;
import com.mtl.cypw.order.exception.refund.OrderRefundException;
import com.mtl.cypw.order.exception.sync.OrderSyncException;
import com.mtl.cypw.order.exception.ticket.MatchStatusException;
import com.mtl.cypw.order.exception.ticket.OrderTicketException;
import com.mtl.cypw.order.exception.ticket.ThirdPartyTicketException;
import com.mtl.cypw.order.mapper.OrderDeliveryMapper;
import com.mtl.cypw.order.mapper.OrderMapper;
import com.mtl.cypw.order.model.*;
import com.mtl.cypw.order.repository.OrderRepository;
import com.mtl.cypw.order.service.CodeRepositoryService;
import com.mtl.cypw.order.service.OrderInventoryConsumeService;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.order.service.OrderService;
import com.mtl.cypw.order.service.OrderSmsEntrance;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.ProgramService;
import com.mtl.cypw.stock.exception.StockBizException;
import com.mtl.cypw.stock.exception.StockInvalidException;
import com.mtl.cypw.stock.service.SeatLockService;
import com.mtl.cypw.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-27 22:53
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private EventService eventService;

    @Autowired
    private StockService stockServiceImpl;

    @Autowired
    private SeatLockService seatLockServiceImpl;

    @Autowired
    private ProgramService programService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Autowired
    private CodeRepositoryService codeRepositoryServiceImpl;

    @Autowired
    private PromotionCouponService promotionCouponService;

    @Autowired
    private CypwApolloConfig cypwApolloConfig;

    @Autowired
    private OrderSmsEntrance orderSmsEntrance;

    @Autowired
    private OrderInventoryConsumeService orderInventoryConsumeServiceImpl;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    private static final List<Integer> CHECK_TYPES = Lists.newArrayList(DeliverTypeEnum.E_TICKET.getCode());

    private static final List<Integer> FETCH_TYPES = Lists.newArrayList(DeliverTypeEnum.OFFLINE.getCode(),
            DeliverTypeEnum.SPOT_PICKING.getCode());


    @Override
    public Order save(Order order) throws OrderBizException {
        return orderRepository.store(order);
    }

    @Override
    public Order lock(Order order) throws OrderLockException {
        log.info(String.format("try lock order for orderId ={}", order.getId()));
        Date current = DateUtils.now();
        if (order.isSystemOrder()) {
            order.lockSuccess(current, 0);
            order.paySuccess(current);
        } else {
            order.lockSuccess(current, cypwApolloConfig.getPaymentTimeout());
        }
        return order;
    }

    @Override
    public Order paid(Order order) throws OrderPaidException {
        log.info(String.format("paid order for orderId ={}", order.getId()));
        return orderRepository.store(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order ticket(Order order) throws OrderTicketException {
        log.info(String.format("order ticket for orderId ={}", order.getId()));
        try {
            boolean isBuildFetchCode = buildFetchCode(order);
            boolean isBuildCheckCode = buildCheckCode(order);
            if (isBuildCheckCode && isBuildFetchCode) {
                order.ticketSuccess(new Date());
                order.setNeedSendPaySuccessMsg(true);
                if (order.getDelivery() != null && order.getDelivery().isDeliveryCompleted()) {
                    order.delivered();
                }
            } else {
                order.startTicket();
            }
            orderRepository.store(order);
            ThreadPoolUtil.execute(() -> {
                log.info("asyn send message, orderNo [{}], sendtime [{}]", order.getOrderNo(),
                        DateUtils.format(DateUtils.now(), DateUtils.millisecondFormat));
                orderSmsEntrance.sendTicketSuccessMessage(order);
            });

            return order;
        } catch (OrderTicketException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Order refund(OrderRefundParam request) throws OrderRefundException {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order cancel(OrderCancelParam request) throws OrderCancelException {
        log.info(String.format("order cancel for orderId ={}", request.getOrderId()));
        Order order = orderQueryServiceImpl.findOneById(request.getOrderId());
        if (order == null) {
            log.error("不存在的订单, order[{}]", request.getOrderId());
            throw new OrderBizException("不存在的订单");
        }
        if (order.isCanceled()) {
            log.error("订单已被取消, order[{}]", request.getOrderId());
            throw new AlreadyCancelledException("订单已被取消");
        }
        if (!order.canCancel()) {
            log.error("该订单不能被取消, order[{}], status[{}]", request.getOrderId(),
                    OrderStatusEnum.getObject(order.getOrderStatus()).getName());
            throw new UnableToCancelException("该订单不能被取消");
        }
        if (request.isValidationUser() && !order.isOwned(request.getUserId())) {
            log.error("只能取消自己的订单, order[{}], status[{}]", request.getOrderId(),
                    OrderStatusEnum.getObject(order.getOrderStatus()).getName());
            throw new NotOwnedException("只能取消自己的订单");
        }
        try {
            // 1. 回滚订单状态/库存
            if (OrderCancelEnum.PAY_TIMEOUT.getCode() == request.getCancelEnum().getCode()) {
                order.timeoutCancel();
            } else {
                order.cancel();
            }
            this.save(order);
            this.releaseInventoryByOrderId(order);
            // 2. 回滚优惠券/兑换券
            this.releaseCouponByOrderId(order);
            // 3. 回滚礼品卡
        } catch (OrderCancelException cancel) {
            throw cancel;
        }
        return order;
    }



    @Override
    public Order syncOrder(Order order) throws OrderSyncException {
        return null;
    }

    @Override
    public Order lockInventory(Order order) throws ValidationException, LockInventoryException {
        if (order == null || order.getInventoryConsumeRecord() == null || !order.isOnlySeat()) {
            log.error("order.lockInventory parameter error, order={}" , JsonUtils.toJson(order));
            throw new ValidationException("订单数据异常", ErrorCode.ERROR_ORDER.getCode());
        }
        OrderInventoryConsumeRecord consumeRecord = order.getInventoryConsumeRecord();
        consumeRecord.setOrderId(order.getId());
        consumeRecord.setSerialNo(order.getOrderNo());
        consumeRecord.setConsumeTime(System.currentTimeMillis());
        consumeRecord.setConsumeStatus(ConsumeStatusEnum.ALREADY_LOCKED.getCode());
        consumeRecord.setConsumeType(ConsumeTypeEnum.SEAT_TICKET.getCode());
        OrderInventoryConsumeRecord record = orderInventoryConsumeServiceImpl.insert(consumeRecord);
        lockSeats(order);
        consumeRecord.setId(record.getId());
        return order;
    }

    private void lockSeats(Order order) {
        SeatLockParam lockParam = buildLockSeatRequest(order);
        boolean locked = false;
        try {
            locked = seatLockServiceImpl.lockSeats(lockParam);
        } catch (Exception e) {
            log.error("SeatLockService.lockSeats exception, param = {}", JsonUtils.toJson(lockParam), e);
        }
        if (!locked) {
            throw new LockInventoryException(ErrorCode.ERROR_ORDER_LOCK_INVENTORY_FAILURE.getMsg());
        }
    }

    private SeatLockParam buildLockSeatRequest(Order order) {
        SeatLockParam lockParam = new SeatLockParam();
        OrderInventoryConsumeRecord consumeRecord = order.getInventoryConsumeRecord();
        if (consumeRecord != null) {
            lockParam.setEnterpriseId(consumeRecord.getEnterpriseId());
            lockParam.setEventId(consumeRecord.getEventId());
            lockParam.setMemberId(order.getMemberId());
            lockParam.setOrderId(order.getId());
        }
        lockParam.setSeatIds(order.getSeatIdSet());
        if (order.isReserveOrder()) {
            lockParam.setSellType(SeatSellTypeEnum.RESERVE);
        } else {
            lockParam.setSellType(SeatSellTypeEnum.SALE);
        }
        return lockParam;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order orderInventoryAndStore(Order order) throws OrderLockException {
        try {
            order = orderRepository.store(order);
            if (order.getInventoryConsumeRecord() != null
                    && !order.getInventoryConsumeRecord().brandNew()) {
                orderInventoryConsumeServiceImpl.orderInventory(order);
            }
            return order;
        } catch (LockInventoryException ie) {
            throw ie;
        } catch (Exception e) {
            throw new LockInventoryException(ErrorCode.ERROR_ORDER_LOCK_INVENTORY_FAILURE.getMsg());
        }

    }

    @Override
    public Order releaseInventoryByOrderId(Order order) throws CancelRollbackInventoryException {
        OrderInventoryConsumeRecord consumeRecord = null;
        if (order.getInventoryConsumeRecord() == null) {
            consumeRecord = orderInventoryConsumeServiceImpl.findOneByOrderId(order.getId());
        }
        if (consumeRecord == null || consumeRecord.getConsumeStatus() == ConsumeStatusEnum.RELEASED.getCode()) {
            order.setInventoryConsumeRecord(null);
            return order;
        }
        try {
            if (ConsumeTypeEnum.SEAT_TICKET.getCode() != consumeRecord.getConsumeType()) {
                StockRollbackWithRecordParam param = new StockRollbackWithRecordParam();
                param.setOrderId(order.getId());
                param.setSerialNo(order.getOrderNo());
                param.setEnterpriseId(order.getEnterpriseId());
                stockServiceImpl.rollbackWithRecord(param);
            } else {
                SeatReleaseParam param = new SeatReleaseParam();
                param.setEnterpriseId(order.getEnterpriseId());
                param.setOrderId(order.getId());
                if (order.isReserveOrder()) {
                    param.setSellType(SeatSellTypeEnum.RESERVE);
                } else {
                    param.setSellType(SeatSellTypeEnum.SALE);
                }
                seatLockServiceImpl.unlockSeats(param);
            }
        } catch (Exception e) {
            throw new CancelRollbackInventoryException("库存释放失败");
        }
        consumeRecord.setConsumeStatus(ConsumeStatusEnum.RELEASED.getCode());
        orderInventoryConsumeServiceImpl.update(consumeRecord);
        order.setInventoryConsumeRecord(null);
        return order;
    }

    @Override
    public Order releaseCouponByOrderId(Order order) {
        List<OrderDiscountDetail> discountDetails = orderRepository.findDiscountDetailsByOrderId(order.getId());
        if (CollectionUtils.isEmpty(discountDetails)) {
            return order;
        }
        try {
            discountDetails.forEach(detail -> promotionCouponService.unlock(detail.getCouponId(), order.getOrderNo()));
        } catch (Exception e) {
            throw new CancelRollbackCouponException(ErrorCode.ERROR_ORDER_CANCEL_ROLLBACK_COUPON_ERROR.getMsg());
        }
        return order;
    }

    @Override
    public Order tryLockStock(Order order) {
        if (order == null || order.getInventoryConsumeRecord() == null || order.isOnlySeat()) {
            log.error("order.tryLockStock parameter error, order={}" , JsonUtils.toJson(order));
            throw new LockInventoryException("订单数据异常");
        }
        OrderInventoryConsumeRecord consumeRecord = order.getInventoryConsumeRecord();
        consumeRecord.setOrderId(order.getId());
        consumeRecord.setSerialNo(order.getOrderNo());
        consumeRecord.setConsumeTime(System.currentTimeMillis());
        consumeRecord.setConsumeStatus(ConsumeStatusEnum.ALREADY_LOCKED.getCode());
        if (order.isGoods()) {
            consumeRecord.setConsumeType(ConsumeTypeEnum.GOODS.getCode());
        } else {
            consumeRecord.setConsumeType(ConsumeTypeEnum.TICKET.getCode());
        }
        OrderInventoryConsumeRecord record = orderInventoryConsumeServiceImpl.insert(consumeRecord);
        consumeRecord.setId(record.getId());
        try {
            boolean consume = stockServiceImpl.consumeWithRecord(OrderBuilder.buildStockConsumeRecord(order));
            if (consume) {
                return order;
            }
        } catch (StockInvalidException sie) {
            throw new LockInventoryException(sie.getMessage());
        } catch (StockBizException sbe) {
            throw new LockInventoryException(sbe.getMessage());
        }
        log.error("order.tryLockStock failed, order={}" , JsonUtils.toJson(order));
        throw new LockInventoryException("订单预扣库存异常");
    }

    private boolean buildFetchCode(Order order) {
        if (!FETCH_TYPES.contains(order.getDelivery().getDeliverType())) {
            return true;
        }
        if (StringUtils.isNotBlank(order.getDelivery().getFetchCode())) {
            return true;
        }
        boolean isSuccess = true;
        List<String> fetchCodeStrList = generateFetchCode(String.format("%s_%s_%s",
                order.getId(), order.getOrderTickets().get(0).getPriceId(), CodeTypeEnum.FETCH_TICKET_CODE.getCode()), 1);
        if (CollectionUtils.isEmpty(fetchCodeStrList)) {
            log.info("generate fetch code result list is empty, orderId={}", order.getOrderNo());
            return false;
        }
        String fetchCodeStr = fetchCodeStrList.get(0);
        if (StringUtils.isNotBlank(fetchCodeStr)) {
            String fetchCode = String.format("%s%s", CodeTypeEnum.FETCH_TICKET_CODE.getCode(), fetchCodeStr);
            order.getDelivery().setFetchCode(fetchCode);
            order.getDelivery().setFetchQrcode(fetchCode);
            order.setFetchCode(fetchCode);
            order.setFetchQrcode(fetchCode);
        } else {
            order.getDelivery().setFetchCode(null);
            order.getDelivery().setFetchQrcode(null);
            isSuccess = false;
        }
        return isSuccess;
    }


    private List<String> generateFetchCode(String sno, int count) {
        List<String> resultList = Lists.newArrayList();
        int total = codeRepositoryServiceImpl.countByBitAndStatus(cypwApolloConfig.getBitLength(), CodeStatusEnum.NOT_USED.getCode());
        if (total < count) {
            ThreadPoolUtil.execute(() -> codeRepositoryServiceImpl.codeGenerate());
            return resultList;
        }
        CodeRepository request = new CodeRepository();
        request.setBitLength(cypwApolloConfig.getBitLength());
        request.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
        request.setStatus(CodeStatusEnum.NOT_USED.getCode());
        List<CodeRepository> codes = codeRepositoryServiceImpl.findListByStatusAndLimitNumber(request, count);
        if (CollectionUtils.size(codes) != count) {
            ThreadPoolUtil.execute(() -> codeRepositoryServiceImpl.codeGenerate());
            return resultList;
        }
        CodeRepository code = codes.get(0);
        resultList.add(code.getCode());
        code.setStatus(CodeStatusEnum.USED.getCode());
        code.setSerialNo(sno);
        codeRepositoryServiceImpl.bulkUpdate(Lists.newArrayList(code));
        return resultList;
    }


    public boolean buildCheckCode(Order order) {
        if (order.isGoods() || CollectionUtils.isEmpty(order.getOrderTickets())) {
            return true;
        }
        Integer eventId = order.getEventId();
        Integer productId = order.getProductId();
        if (eventId <= 0 || productId <= 0) {
            return false;
        }
        Event event = eventService.findOneById(eventId);
        if (event == null) {
            return false;
        }

        Program program = programService.getProgramById(productId);
        if (program == null) {
            return false;
        }
        /**
         * 场次检票码来源
         */
        boolean isThirdPartyCheckCode = BooleanUtils.toBoolean(event.getCodeSource() == CodeSourceEnum.THIRD_PARTY_CODE.getCode());
        /**
         * 是否需要自有检票码
         */
        boolean needSelfCheckCode = CHECK_TYPES.contains(order.getDelivery().getDeliverType())
                        && event.getCodeSource() == CodeSourceEnum.SELF_CODE.getCode();
        if (!needSelfCheckCode && !isThirdPartyCheckCode) {
            return true;
        }
        if (isThirdPartyCheckCode) {
            generateThirdPartyCode(eventId, order.getOrderTickets());
        } else if (needSelfCheckCode) {
            generateSelfCode(eventId, order.getOrderTickets());
        }
        return true;
    }

    private boolean generateSelfCode(Integer eventId, List<OrderTicket> orderTickets) {
        if (CollectionUtils.isEmpty(orderTickets)) {
            return true;
        }
        Map<Integer, List<OrderTicket>> priceIdTicketsMap = orderTickets.stream().collect(Collectors.groupingBy(OrderTicket :: getPriceId));
        for (Map.Entry<Integer, List<OrderTicket>> entry : priceIdTicketsMap.entrySet()) {
            Integer priceId = entry.getKey();
            List<OrderTicket> tickets = entry.getValue();
            int count = tickets.size();
            int total = codeRepositoryServiceImpl.countByBitAndStatus(cypwApolloConfig.getBitLength(), CodeStatusEnum.NOT_USED.getCode());
            if (total < count) {
                ThreadPoolUtil.execute(() -> codeRepositoryServiceImpl.codeGenerate());
                return false;
            }
            CodeRepository request = new CodeRepository();
            request.setBitLength(cypwApolloConfig.getBitLength());
            request.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
            request.setStatus(CodeStatusEnum.NOT_USED.getCode());
            List<CodeRepository> codes = codeRepositoryServiceImpl.findListByStatusAndLimitNumber(request, count);
            if (codes.size() != count) {
                log.error("Self check code is not enough, eventId[{}], priceId[{}]", eventId, priceId);
                throw new MatchStatusException("电子票库存不足");
            }
            int codeIndex = 0;
            for (OrderTicket ticket : tickets) {
                CodeRepository code = codes.get(codeIndex);
                this.setCheckCodeToOrderTicket(ticket, code.getCodeSource(), code.getCode());
                code.setStatus(CodeStatusEnum.USED.getCode());
                code.setSerialNo(String.format("%s_%s_%s", ticket.getOrderId(), ticket.getId(), CodeTypeEnum.CHECK_CODE.getCode()));
                codeIndex++;
            }
            codeRepositoryServiceImpl.bulkUpdate(codes);
        }
            return true;
    }

    private boolean generateThirdPartyCode(Integer eventId, List<OrderTicket> orderTickets) {
        if (CollectionUtils.isEmpty(orderTickets)) {
            return true;
        }
        Map<Integer, List<OrderTicket>> priceIdTicketsMap = orderTickets.stream().collect(Collectors.groupingBy(OrderTicket :: getPriceId));
        for (Map.Entry<Integer, List<OrderTicket>> entry : priceIdTicketsMap.entrySet()) {
            Integer priceId = entry.getKey();
            List<OrderTicket> tickets = entry.getValue();
            int count = tickets.size();
            CodeRepository request = new CodeRepository();
            request.setEventId(eventId);
            request.setPriceId(priceId);
            int total = codeRepositoryServiceImpl.countByThirdPartyUsable(request, CodeStatusEnum.NOT_USED.getCode());
            if (total < count) {
                log.error("No Third-party check code is available, eventId[{}], priceId[{}]", eventId, priceId);
                throw new ThirdPartyTicketException("第三方电子票库存不足");
            }
            request.setCodeSource(CodeSourceEnum.THIRD_PARTY_CODE.getCode());
            request.setStatus(CodeStatusEnum.NOT_USED.getCode());
            List<CodeRepository> codes = codeRepositoryServiceImpl.findListByStatusAndLimitNumber(request, count);
            if (codes.size() != count) {
                log.error("Third-party check code is not enough, eventId[{}], priceId[{}]", eventId, priceId);
                throw new ThirdPartyTicketException("第三方电子票库存不足");
            }
            int codeIndex = 0;
            for (OrderTicket ticket : tickets) {
                CodeRepository code = codes.get(codeIndex);
                this.setCheckCodeToOrderTicket(ticket, code.getCodeSource(), code.getCode());
                code.setStatus(CodeStatusEnum.USED.getCode());
                code.setSerialNo(String.format("%s_%s_%s", ticket.getOrderId(), ticket.getId(), CodeTypeEnum.CHECK_CODE.getCode()));
                codeIndex++;
            }
            codeRepositoryServiceImpl.bulkUpdate(codes);
        }
        return true;
    }

    private void setCheckCodeToOrderTicket(OrderTicket orderTicket, int codeSource, String code) {
        if (CodeSourceEnum.SELF_CODE.getCode() == codeSource) {
            code = String.format("%s%s", CodeTypeEnum.CHECK_CODE.getCode(), code);
        }
        /**
         * 检票码
         */
        orderTicket.setCheckCode(code);
        /**
         * 二维码生成规则
         */
        orderTicket.setQrCode(code);
        orderTicket.setCodeSource(codeSource);
    }

    /**
     * 确认收货
     *
     * @param orderId 订单id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmReceipt(int orderId) {
        Order order = orderRepository.findOneById(orderId);
        ValidCommon(order);
        if (!order.isDelivering() || !order.getDelivery().isDelivering()) {
            throw new OrderBizException("该订单不在配送中,无法确认收货");
        }
        int countByOrder = orderMapper.updateOrderStatusByConfirm(orderId, OrderStatusEnum.DELIVERING.getCode(), OrderStatusEnum.DELIVERED.getCode());
        if (countByOrder != 1) {
            throw new OrderBizException("确认收货更新失败");
        }
        int countByDelivery = updateDeliveryStatusByConfirm(order);
        if (countByDelivery != 1) {
            throw new OrderBizException("物流状态更新异常");
        }
    }


    /**
     * 通用校验(确认收货+绑定物流单号)
     *
     * @param order 订单实体类参数
     */
    private void ValidCommon(Order order) {
        if (null == order) {
            throw new OrderBizException("该订单不存在");
        }
        if (null == order.getDelivery()) {
            throw new OrderBizException("该订单没有交付明细信息,无法绑定物流单号");
        }
        if (order.getDelivery().isDeliveryCompleted()) {
            throw new OrderBizException("该订单不属于快递配送,无法绑定物流单号");
        }
    }

    /**
     * 确认收货更新物流状态为已配送
     *
     * @param order 订单实体类参数
     * @return 更新数量
     */
    private int updateDeliveryStatusByConfirm(Order order) {
        Example example = new Example(OrderDelivery.class);
        Example.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andEqualTo("orderId", order.getId());
        exampleCriteria.andEqualTo("deliveryStatus", order.getDelivery().getDeliveryStatus());
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setDeliveryStatus(DeliverStatusEnum.DELIVERED.getCode());
        return orderDeliveryMapper.updateByExampleSelective(orderDelivery, example);
    }

    /**
     * 绑定物流单号
     *
     * @param request 绑定参数
     * @return 订单实体对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order bindExpressNo(BindExpressNoParam request) {
        Order order = orderRepository.findOneByOrderNo(request.getOrderNo());
        ValidCommon(order);
        if (!order.isDelivered() || !order.getDelivery().isUndelivered()) {
            throw new OrderBizException("该订单不是待收货订单,无法绑定物流单号");
        }
        int countByOrder = updateOrderStatusByBindExpressNo(order);
        if (countByOrder != 1) {
            throw new OrderBizException("订单状态更新异常");
        }
        order.bindExpressNoSuccess();
        OrderDelivery orderDelivery = order.getDelivery();
        orderDelivery.setExpressNo(request.getExpressNo());
        //更新deliverystatus = DELIVERING(配送中)
        int countByDelivery = orderDeliveryMapper.updateExpressNo(orderDelivery, DeliverStatusEnum.UNDELIVERED.getCode(), DeliverStatusEnum.DELIVERING.getCode());
        if (countByDelivery != 1) {
            throw new OrderBizException("物流状态更新异常");
        }
        return order;
    }

    /**
     * 绑定物流单号更新订单状态为发货中
     *
     * @param order 订单实体类参数
     * @return 更新数量
     */
    private int updateOrderStatusByBindExpressNo(Order order) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", order.getId());
        criteria.andEqualTo("orderStatus", order.getOrderStatus());
        Order orderParam = new Order();
        orderParam.setOrderStatus(OrderStatusEnum.DELIVERING.getCode());
        return orderMapper.updateByExampleSelective(orderParam, example);
    }

}
