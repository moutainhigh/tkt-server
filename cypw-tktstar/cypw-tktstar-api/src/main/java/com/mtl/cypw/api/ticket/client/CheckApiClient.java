package com.mtl.cypw.api.ticket.client;

import com.mtl.cypw.api.ticket.endpoint.CheckInApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 21:27
 */
@FeignClient(name = "cypw-tktstar")
public interface CheckApiClient extends CheckInApi {

}
