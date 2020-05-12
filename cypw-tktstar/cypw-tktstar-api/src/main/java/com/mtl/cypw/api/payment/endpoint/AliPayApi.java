package com.mtl.cypw.api.payment.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.payment.param.AliPayRequestParam;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.result.AliPayResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/9.
 */
public interface AliPayApi {

    /**
     * 阿里支付
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/payment/ali/unified/order")
    TSingleResult<AliPayResult> unifiedOrder(@RequestBody GenericRequest<AliPayRequestParam> request);

    /**
     * 阿里支付验签
     * @param request
     * @return
     */
    @PostMapping("/endpoint/payment/ali/pay/check_sign")
    TSingleResult<Boolean> checkSign(@RequestBody GenericRequest<PayCheckSignParam> request);
}
