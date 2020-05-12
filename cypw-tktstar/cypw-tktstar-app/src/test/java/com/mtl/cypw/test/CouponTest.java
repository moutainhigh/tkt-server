package com.mtl.cypw.test;

import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.coupon.service.PromotionService;
import com.mtl.cypw.domain.coupon.param.PromotionQueryParam;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public class CouponTest extends BaseTest{

    @Resource
    PromotionService promotionService;


    @Test
    public void getPromotion(){
        PromotionQueryParam param = new PromotionQueryParam();
        param.setNotPromotionPointTypes(Arrays.asList(1));
        List<Integer> list = new ArrayList<>();
        list.add(82);
        list.add(81);
        param.setProgramTypeIds(list);
        List<Promotion> promotions = promotionService.searchPromotionList(param);
        System.out.println(promotions.size());
    }
}
