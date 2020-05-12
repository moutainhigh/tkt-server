package com.mtl.cypw.api.coupon.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.domain.coupon.dto.PromotionDTO;
import com.mtl.cypw.domain.coupon.param.PromotionQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public interface PromotionApi {

    /**
     * 查询优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/coupon/promotions")
    TMultiResult<PromotionDTO> searchPromotionList(@RequestBody QueryRequest<PromotionQueryParam> request);
}
