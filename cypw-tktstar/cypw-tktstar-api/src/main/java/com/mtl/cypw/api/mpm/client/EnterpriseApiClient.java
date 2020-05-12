package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.EnterpriseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@FeignClient(name = "cypw-tktstar")
public interface EnterpriseApiClient extends EnterpriseApi {
}
