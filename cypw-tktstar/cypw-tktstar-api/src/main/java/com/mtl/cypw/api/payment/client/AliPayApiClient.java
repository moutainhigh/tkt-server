package com.mtl.cypw.api.payment.client;

import com.mtl.cypw.api.payment.endpoint.AliPayApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@FeignClient(name = "cypw-tktstar")
public interface AliPayApiClient extends AliPayApi {
}
