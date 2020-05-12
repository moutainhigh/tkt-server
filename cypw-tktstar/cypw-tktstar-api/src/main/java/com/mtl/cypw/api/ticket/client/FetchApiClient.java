package com.mtl.cypw.api.ticket.client;

import com.mtl.cypw.api.ticket.endpoint.FetchApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 17:40
 */
@FeignClient(name = "cypw-tktstar")
public interface FetchApiClient extends FetchApi {

}
