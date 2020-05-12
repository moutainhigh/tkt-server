package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.order.model.Order;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:20
 */
public interface UniformOrderService {

    Order transform(UniformCreateOrderParam request);

    Order init(Order order, UniformCreateOrderParam request);

    Order lock(Order order);

    Order ticket(Order order);

    void removeCart(Order order);

}
