package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.BannerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author zhengyou.yuan
 * @date 2019-11-23 11:46
 */
@FeignClient(name = "cypw-tktstar")
public interface BannerApiClient extends BannerApi {

}
