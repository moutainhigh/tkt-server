package com.mtl.cypw.order.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.model.Order;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:20
 */
public interface OrderQueryService {

    Order findOneById(Integer orderId);

    Order findOneByOrderNo(String orderNo);

    List<Order> findPageAll(OrderQueryParam param, Pagination pagination);

    Integer countQuery(OrderQueryParam param);

    Order findOneByFetchCode(String fetchCode, Integer enterpriseId);

}
