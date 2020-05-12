package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.ShortageRegistrationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/2.
 */
@FeignClient(name = "cypw-tktstar")
public interface ShortageRegistrationApiClient extends ShortageRegistrationApi {
}
