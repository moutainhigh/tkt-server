package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.ProgramApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/19.
 */
@FeignClient(name = "cypw-tktstar")
public interface ProgramApiClient extends ProgramApi {


}
