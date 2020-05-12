package com.mtl.cypw.payment.service;

import com.mtl.cypw.payment.mapper.PaymentLogMapper;
import com.mtl.cypw.payment.pojo.PaymentLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/6.
 */
@Service
public class PaymentLogService {

    @Resource
    PaymentLogMapper mapper;

    public void addPaymentLog(PaymentLog paymentLog) {

        mapper.insert(paymentLog);
    }

    public void updateStatus(Integer orderId){

        mapper.updateStatus(orderId);
    }
}
