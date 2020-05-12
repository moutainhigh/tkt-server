package com.mtl.cypw.api.coupon.client;

import com.mtl.cypw.api.coupon.endpoint.PromotionPriceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tang.
 * @date 2019/12/5.
 */
@FeignClient(name = "cypw-tktstar")
public interface PromotionPriceApiClient extends PromotionPriceApi {
}
