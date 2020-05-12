package com.mtl.cypw.api.member.client;

import com.mtl.cypw.api.member.endpoint.MemberApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang
 * @date 2019-11-17 22:49
 */
@FeignClient(name = "cypw-tktstar")
public interface MemberApiClient extends MemberApi {
}
