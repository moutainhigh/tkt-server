package com.mtl.cypw.api.payment.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.param.CmbPayRequestParam;
import com.mtl.cypw.domain.payment.result.CmbPayResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/31.
 */
public interface CmbPayApi {

    @PostMapping("/endpoint/payment/cmb/unified/order")
    TSingleResult<CmbPayResult> unifiedOrder(@RequestBody GenericRequest<CmbPayRequestParam> request);

    @PostMapping("/endpoint/payment/cmb/pay/check_sign")
    TSingleResult<Boolean> checkSign(@RequestBody GenericRequest<PayCheckSignParam> request);
}
