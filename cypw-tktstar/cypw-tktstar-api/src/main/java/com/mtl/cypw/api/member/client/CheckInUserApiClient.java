package com.mtl.cypw.api.member.client;

import com.mtl.cypw.api.member.endpoint.CheckInUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@FeignClient(name = "cypw-tktstar")
public interface CheckInUserApiClient extends CheckInUserApi {
}
