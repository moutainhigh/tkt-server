package com.mtl.cypw.api.mall.client;

import com.mtl.cypw.api.mall.endpoint.MerchandiseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 21:27
 */
@FeignClient(name = "cypw-tktstar")
public interface MerchandiseApiClient extends MerchandiseApi {
}
