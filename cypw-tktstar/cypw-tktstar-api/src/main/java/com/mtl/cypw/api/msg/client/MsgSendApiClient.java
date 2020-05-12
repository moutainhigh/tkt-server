package com.mtl.cypw.api.msg.client;

import com.mtl.cypw.api.msg.endpoint.MsgSendApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@FeignClient(name = "cypw-tktstar")
public interface MsgSendApiClient extends MsgSendApi {
}
