package com.mtl.cypw.api.coupon.endpoint;

import com.juqitech.request.IdListRequest;
import com.juqitech.response.TMultiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/5.
 */
public interface PromotionPriceApi {


    /**
     * 查询场次所对应的兑换券id（promotionId）
     * @param request
     * @return
     */
    @PostMapping("/endpoint/promotion_price/promotion_ids")
    TMultiResult<Integer> searchPromotionIdsByEventId(@RequestBody IdListRequest request);


}
