package com.mtl.cypw.api.wx.client;

import com.mtl.cypw.api.wx.endpoint.OffiaccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/10.
 */
@FeignClient(name = "cypw-tktstar")
public interface OffiaccountApiClient extends OffiaccountApi {
}
