package com.mtl.cypw.provider.payment.service;

import com.juqitech.response.TSingleResult;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.param.OrderPaidParam;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.payment.pojo.PaymentLog;
import com.mtl.cypw.payment.service.PaymentLogService;
import com.mtl.cypw.provider.order.service.biz.OrderOpEntrance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/6.
 */
@Service
public class PaymentCallbackService {

    @Resource
    private OrderOpEntrance orderOpEntrance;

    @Resource
    private PaymentLogService paymentLogService;

    public TSingleResult<Boolean> paid(Order order, PayTypeEnum payTypeEnum, String responseStr) {
        addPaymentLog(order, payTypeEnum, responseStr);

        OrderPaidParam param = new OrderPaidParam();
        param.setOrderId(order.getId());
        param.setOrderPrice(MoneyUtils.getMoneyByCent(order.getActualAmount()));
        param.setPaySuccessTime(DateUtils.now());
        param.setPayType(payTypeEnum);
        param.setSerialNo(order.getTransactionFlowNo());
        return orderOpEntrance.paidOrder(param);
    }

    private void addPaymentLog(Order order, PayTypeEnum payTypeEnum, String responseStr) {
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setOrderId(order.getId());
        paymentLog.setEnterpriseId(order.getEnterpriseId());
        paymentLog.setPaymentType(payTypeEnum.getPayName());
        paymentLog.setTransactionFlowNo(order.getTransactionFlowNo());
        paymentLog.setAmount(order.getActualAmount().toString());
        paymentLog.setStatus("INIT");
        paymentLog.setTransactionInfo(responseStr);
        paymentLog.setCreateTime(DateUtils.now());
        paymentLogService.addPaymentLog(paymentLog);
    }
}
