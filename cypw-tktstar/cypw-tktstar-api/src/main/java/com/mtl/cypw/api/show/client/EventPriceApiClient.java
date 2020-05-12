package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.EventPriceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@FeignClient(name = "cypw-tktstar")
public interface EventPriceApiClient extends EventPriceApi {
}
