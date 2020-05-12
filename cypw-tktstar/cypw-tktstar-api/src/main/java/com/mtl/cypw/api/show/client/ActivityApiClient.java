package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.ActivityApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@FeignClient(name = "cypw-tktstar")
public interface ActivityApiClient extends ActivityApi {
}
