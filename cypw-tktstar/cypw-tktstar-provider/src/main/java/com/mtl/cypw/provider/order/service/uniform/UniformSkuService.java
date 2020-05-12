package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.model.OrderItem;
import com.mtl.cypw.order.model.OrderTicket;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-03 10:55
 */
public interface UniformSkuService {

    List<OrderDiscountDetail> buildOrderDiscountDetails(Order order);

    List<OrderItem> buildOrderItems(Order order);

    List<OrderTicket> buildOrderTickets(Order order);


}
