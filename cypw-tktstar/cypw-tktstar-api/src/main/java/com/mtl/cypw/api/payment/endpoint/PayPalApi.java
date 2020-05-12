package com.mtl.cypw.api.payment.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.payment.param.PayPalExecutePaymentParam;
import com.mtl.cypw.domain.payment.param.PayPalRequestParam;
import com.mtl.cypw.domain.payment.result.PayPalResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/2/14.
 */
public interface PayPalApi {

    @PostMapping("/endpoint/payment/paypal/create")
    TSingleResult<PayPalResult> createPayment(@RequestBody GenericRequest<PayPalRequestParam> request);

    @PostMapping("/endpoint/payment/paypal/execute")
    TSingleResult<Integer> executePayment(@RequestBody GenericRequest<PayPalExecutePaymentParam> request);
}
