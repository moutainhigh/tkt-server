package com.mtl.cypw.coupon.service;

import com.mtl.cypw.coupon.mapper.PromotionMapper;
import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.domain.coupon.param.PromotionQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Service
@Slf4j
public class PromotionService {

    @Resource
    PromotionMapper mapper;

    public List<Promotion> searchPromotionList(PromotionQueryParam param) {
        return mapper.searchPromotion(param);
    }

    public Promotion getPromotionById(Integer promotionId) {
        if (promotionId == null) {
            log.error("promotion is null");
            return null;
        }
        return mapper.selectByPrimaryKey(promotionId);
    }

    public void updateHaveExchangeQty(Integer promotionId) {
        updateHaveExchangeQty(promotionId, 1);
    }

    public void updateHaveExchangeQty(Integer promotionId, int count) {
        Promotion promotion = getPromotionById(promotionId);
        if (promotion != null) {
            int qty = promotion.getHaveExchangeQty() == null ? 0 : promotion.getHaveExchangeQty();
            count = count + qty;
            promotion.setHaveExchangeQty(count);
            update(promotion);
        }
    }

    public void update(Promotion promotion) {
        if (promotion != null) {
            mapper.updateByPrimaryKeySelective(promotion);
        }

    }
}
