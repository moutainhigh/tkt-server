package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.SmsMessageApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@FeignClient(name = "cypw-tktstar")
public interface SmsMessageApiClient extends SmsMessageApi {
}
