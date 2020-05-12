package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.TheatreApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@FeignClient(name = "cypw-tktstar")
public interface TheatreApiClient extends TheatreApi {
}
