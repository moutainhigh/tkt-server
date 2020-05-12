package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.ActorApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@FeignClient(name = "cypw-tktstar")
public interface ActorApiClient extends ActorApi {
}
