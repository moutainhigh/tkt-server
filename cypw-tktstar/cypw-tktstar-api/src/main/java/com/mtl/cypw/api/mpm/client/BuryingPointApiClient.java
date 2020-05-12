package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.BuryingPointApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@FeignClient(name = "cypw-tktstar")
public interface BuryingPointApiClient extends BuryingPointApi {
}
