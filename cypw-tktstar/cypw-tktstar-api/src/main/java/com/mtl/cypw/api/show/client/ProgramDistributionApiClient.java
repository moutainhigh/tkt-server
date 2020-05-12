package com.mtl.cypw.api.show.client;

import com.mtl.cypw.api.show.endpoint.ProgramDistributionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@FeignClient(name = "cypw-tktstar")
public interface ProgramDistributionApiClient extends ProgramDistributionApi {
}
