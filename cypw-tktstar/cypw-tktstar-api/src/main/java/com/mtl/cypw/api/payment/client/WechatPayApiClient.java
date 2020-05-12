package com.mtl.cypw.api.payment.client;

import com.mtl.cypw.api.payment.endpoint.WechatPayApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/6.
 */
@FeignClient(name = "cypw-tktstar")
public interface WechatPayApiClient extends WechatPayApi{
}
