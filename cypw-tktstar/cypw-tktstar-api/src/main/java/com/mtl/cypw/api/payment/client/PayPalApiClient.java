package com.mtl.cypw.api.payment.client;

import com.mtl.cypw.api.payment.endpoint.PayPalApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@FeignClient(name = "cypw-tktstar")
public interface PayPalApiClient extends PayPalApi {
}
