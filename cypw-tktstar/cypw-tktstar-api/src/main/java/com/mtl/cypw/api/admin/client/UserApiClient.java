package com.mtl.cypw.api.admin.client;

import com.mtl.cypw.api.admin.endpoint.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/18.
 */
@FeignClient(name = "cypw-tktstar")
public interface UserApiClient extends UserApi {
}
