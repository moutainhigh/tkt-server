package com.mtl.cypw.api.auth.client;

import com.mtl.cypw.api.auth.endpoint.CmbApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/1/15.
 */
@FeignClient(name = "cypw-tktstar")
public interface CmbApiClient extends CmbApi {
}
