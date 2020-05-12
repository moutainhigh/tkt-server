package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.domain.order.param.UniformSkuParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderItem;


/**
 * @author zhengyou.yuan
 * @date 2019-12-03 11:27
 */
public interface SkuService {

    OrderItem buildOrderItem(Order order, UniformSkuParam param);

}
