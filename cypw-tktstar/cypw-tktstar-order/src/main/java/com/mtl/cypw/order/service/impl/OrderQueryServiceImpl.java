package com.mtl.cypw.order.service.impl;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDiscountDetail;
import com.mtl.cypw.order.repository.OrderRepository;
import com.mtl.cypw.order.service.OrderQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 13:54
 */
@Slf4j
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOneById(Integer orderId) {
        return orderRepository.findOneById(orderId);
    }

    @Override
    public Order findOneByOrderNo(String orderNo) {
        return orderRepository.findOneByOrderNo(orderNo);
    }

    @Override
    public List<Order> findPageAll(OrderQueryParam param, Pagination pagination) {
        return orderRepository.pageQuery(param, pagination);
    }

    @Override
    public Integer countQuery(OrderQueryParam param) {
        return orderRepository.countQuery(param);
    }

    @Override
    public Order findOneByFetchCode(String fetchCode, Integer enterpriseId) {
        return orderRepository.findOneByFetchCode(fetchCode, enterpriseId);
    }
}
