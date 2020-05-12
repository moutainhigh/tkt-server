package com.mtl.cypw.api.member.client;

import com.mtl.cypw.api.member.endpoint.MachineApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@FeignClient(name = "cypw-tktstar")
public interface MachineApiClient extends MachineApi {
}
