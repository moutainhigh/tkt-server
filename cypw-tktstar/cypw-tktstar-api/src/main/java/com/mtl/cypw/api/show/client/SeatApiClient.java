package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.SeatApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-08 18:03
 */
@FeignClient(name = "cypw-tktstar")
public interface SeatApiClient extends SeatApi {
}
