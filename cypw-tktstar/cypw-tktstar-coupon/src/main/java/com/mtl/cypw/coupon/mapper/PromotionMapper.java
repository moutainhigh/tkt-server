package com.mtl.cypw.coupon.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.domain.coupon.param.PromotionQueryParam;

import java.util.List;

/**
 * @author tang.
 * @date 2019年12月04日 上午11:15:46
 */
public interface PromotionMapper extends BaseMapper<Promotion> {

    List<Promotion> searchPromotion(PromotionQueryParam param);
}