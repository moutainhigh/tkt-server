package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.VenueApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@FeignClient(name = "cypw-tktstar")
public interface VenueApiClient extends VenueApi {
}
