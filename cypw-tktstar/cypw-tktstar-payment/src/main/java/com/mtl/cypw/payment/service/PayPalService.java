package com.mtl.cypw.payment.service;

import com.mtl.cypw.common.redis.template.CommonRedisTemplate;
import com.mtl.cypw.domain.payment.config.PayPalConfigure;
import com.mtl.cypw.domain.payment.enums.PayPalPayIntentEnum;
import com.mtl.cypw.domain.payment.enums.PayPalPayTypeEnum;
import com.mtl.cypw.domain.payment.param.PayPalRequestParam;
import com.mtl.cypw.payment.constant.PaymentConstants;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@Service
@Slf4j
public class PayPalService {

    @Autowired
    private CommonRedisTemplate redisTemplate;

    public Payment createPayment(PayPalRequestParam param, PayPalConfigure config) throws PayPalRESTException {
        Payment payment = new Payment();
        //交易对象
        Payer payer = new Payer();
        payer.setPaymentMethod(param.getPayPalPayTypeEnum().toString());
        payment.setPayer(payer);
        //交易类型
        payment.setIntent(param.getPayPalPayIntentEnum().toString());
        //回跳地址
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(param.getCancelUrl());
        redirectUrls.setReturnUrl(param.getReturnUrl());
        payment.setRedirectUrls(redirectUrls);
        //商品信息
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        Amount amount = new Amount();
        amount.setCurrency(config.getCurrencyCode());
        amount.setTotal(param.getOrderAmount().toString());
        transaction.setInvoiceNumber(param.getOrderNo());
        transaction.setAmount(amount);
        transactions.add(transaction);
        payment.setTransactions(transactions);
        payment = payment.create(config.apiContext());
        redisTemplate.saveBean(PaymentConstants.getPayPalConfigKey(payment.getId()), config, 3600);
        return payment;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        log.debug("订单执行，payPal订单id:{}，付款人:{}", paymentId, payerId);
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        PayPalConfigure config = redisTemplate.getBean(PaymentConstants.getPayPalConfigKey(payment.getId()), PayPalConfigure.class);
        return payment.execute(config.apiContext(), paymentExecute);
    }


}
