package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.MachinePosterApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/16.
 */
@FeignClient(name = "cypw-tktstar")
public interface MachinePosterApiClient extends MachinePosterApi {
}
