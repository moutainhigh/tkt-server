package com.mtl.cypw.api.stock.client;

import com.mtl.cypw.api.stock.endpoint.ReserveApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 12:27
 */
@FeignClient(name = "cypw-tktstar")
public interface ReserveApiClient extends ReserveApi {

}
