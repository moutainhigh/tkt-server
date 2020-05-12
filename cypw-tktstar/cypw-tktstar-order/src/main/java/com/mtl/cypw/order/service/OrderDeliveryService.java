package com.mtl.cypw.order.service;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-21 12:10
 */
public interface OrderDeliveryService {

    boolean updateFetchStatusByOrderId(Integer targetStatus, Date fetchTime, Integer orderId, Integer oldStatus);

}
