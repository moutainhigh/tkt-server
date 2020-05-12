package com.mtl.cypw.api.payment.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.param.WechatPayRequestParam;
import com.mtl.cypw.domain.payment.result.WechatPayResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/6.
 */
public interface WechatPayApi {

    @PostMapping("/endpoint/payment/wechat/unified/order")
    TSingleResult<WechatPayResult> unifiedOrder(@RequestBody GenericRequest<WechatPayRequestParam> request);

    @PostMapping("/endpoint/payment/wechat/pay/check_sign")
    TSingleResult<Boolean> checkSign(@RequestBody GenericRequest<PayCheckSignParam> request);
}
