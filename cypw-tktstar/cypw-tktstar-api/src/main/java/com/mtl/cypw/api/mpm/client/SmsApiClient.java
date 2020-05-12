package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.SmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/16.
 */
@FeignClient(name = "cypw-tktstar")
public interface SmsApiClient extends SmsApi {
}
