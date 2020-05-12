package com.mtl.cypw.api.mpm.client;

import com.mtl.cypw.api.mpm.endpoint.TemplateApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@FeignClient(name = "cypw-tktstar")
public interface TemplateApiClient extends TemplateApi {
}
