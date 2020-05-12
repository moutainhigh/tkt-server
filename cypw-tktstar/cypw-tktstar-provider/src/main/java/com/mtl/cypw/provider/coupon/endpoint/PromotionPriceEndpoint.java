package com.mtl.cypw.provider.coupon.endpoint;

import com.google.common.collect.Lists;
import com.juqitech.request.IdListRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.coupon.endpoint.PromotionPriceApi;
import com.mtl.cypw.coupon.service.PromotionPriceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/5.
 */
@RestController
@Slf4j
public class PromotionPriceEndpoint implements PromotionPriceApi {

    @Resource
    private PromotionPriceService promotionPriceService;


    @Override
    public TMultiResult<Integer> searchPromotionIdsByEventId(IdListRequest request) {
        if (CollectionUtils.isEmpty(request.getIdList())) {
            return ResultBuilder.succTMulti(Collections.emptyList());
        }
        List<Integer> eventIds = Lists.transform(request.getIdList(), s -> Integer.parseInt(s));
        List<Integer> promotionIds = promotionPriceService.searchPromotionIdsByEventId(eventIds);
        return ResultBuilder.succTMulti(promotionIds);
    }
}
