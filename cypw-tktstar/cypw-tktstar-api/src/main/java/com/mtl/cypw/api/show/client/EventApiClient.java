package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.EventApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@FeignClient(name = "cypw-tktstar")
public interface EventApiClient extends EventApi {
}
