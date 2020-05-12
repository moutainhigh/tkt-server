package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.DistrictApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@FeignClient(name = "cypw-tktstar")
public interface DistrictApiClient extends DistrictApi {
}
