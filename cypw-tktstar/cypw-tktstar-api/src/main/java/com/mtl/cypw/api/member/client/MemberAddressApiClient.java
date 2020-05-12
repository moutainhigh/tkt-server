package com.mtl.cypw.api.member.client;

import com.mtl.cypw.api.member.endpoint.MemberAddressApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@FeignClient(name = "cypw-tktstar")
public interface MemberAddressApiClient extends MemberAddressApi {
}
