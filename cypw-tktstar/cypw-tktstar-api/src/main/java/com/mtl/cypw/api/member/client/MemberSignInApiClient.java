package com.mtl.cypw.api.member.client;

import com.mtl.cypw.api.member.endpoint.MemberSignInApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@FeignClient(name = "cypw-tktstar")
public interface MemberSignInApiClient extends MemberSignInApi {
}
