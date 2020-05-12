package com.mtl.cypw.api.coupon.client;

import com.mtl.cypw.api.coupon.endpoint.PromotionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@FeignClient(name = "cypw-tktstar")
public interface PromotionApiClient extends PromotionApi {
}
