package com.mtl.cypw.api.order.client;

import com.mtl.cypw.api.order.endpoint.OrderQueryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-17 22:50
 */
@FeignClient(name = "cypw-tktstar")
public interface OrderQueryApiClient extends OrderQueryApi {

}
