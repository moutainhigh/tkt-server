package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.domain.order.param.OrderCouponParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;

/**
 * @author zhengyou.yuan
 * @date 2019-12-15 14:10
 */
public interface SpecialService {

    OrderDiscountDetail buildDiscountDetail(Order order, OrderCouponParam couponParam);

}
