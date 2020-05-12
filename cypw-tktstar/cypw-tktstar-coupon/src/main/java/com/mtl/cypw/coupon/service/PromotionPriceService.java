package com.mtl.cypw.coupon.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mtl.cypw.coupon.mapper.PromotionPriceMapper;
import com.mtl.cypw.coupon.pojo.PromotionPrice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Service
@Slf4j
public class PromotionPriceService {

    @Resource
    PromotionPriceMapper mapper;

    public List<PromotionPrice> searchPromotionPricesByEventId(List<Integer> eventIds) {
        if (CollectionUtils.isEmpty(eventIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(PromotionPrice.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIn("eventId", eventIds);
        return mapper.selectByExample(example);
    }

    public List<Integer> searchPromotionIdsByEventId(List<Integer> eventIds) {
        List<PromotionPrice> promotionPrices = searchPromotionPricesByEventId(eventIds);
        if (CollectionUtils.isEmpty(promotionPrices)) {
            return Collections.emptyList();
        }
        Set<Integer> promotionIds = Sets.newHashSet();
        promotionPrices.forEach(promotionPrice -> promotionIds.add(promotionPrice.getPromotionId()));
        return Lists.newArrayList(promotionIds);
    }
}
