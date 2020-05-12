package com.mtl.cypw.order.service.impl;

import com.mtl.cypw.order.mapper.OrderDeliveryMapper;
import com.mtl.cypw.order.service.OrderDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-21 12:11
 */
@Slf4j
@Service
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Override
    public boolean updateFetchStatusByOrderId(Integer targetStatus, Date fetchTime, Integer orderId, Integer oldStatus) {
        int count = orderDeliveryMapper.updateFetchStatus(targetStatus, fetchTime, orderId, oldStatus);
        return count > 0;
    }
}
