package com.mtl.cypw.api.payment.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.payment.callback.CcbCallbackParam;
import com.mtl.cypw.domain.payment.param.CcbPayRequestParam;
import com.mtl.cypw.domain.payment.result.CcbPayResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/01/08.
 */
public interface CcbPayApi {

    @PostMapping("/endpoint/payment/ccb/unified/order")
    TSingleResult<CcbPayResult> unifiedOrder(@RequestBody GenericRequest<CcbPayRequestParam> request);

    @PostMapping("/endpoint/payment/ccb/pay/check_sign")
    TSingleResult<Boolean> checkSign(@RequestBody GenericRequest<CcbCallbackParam> request);
}
