package com.mtl.cypw.api.payment.client;

import com.mtl.cypw.api.payment.endpoint.CmbPayApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/31.
 */
@FeignClient(name = "cypw-tktstar")
public interface CmbPayApiClient extends CmbPayApi {
}
