package com.mtl.cypw.order.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.order.model.OrderTicket;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
public interface OrderTicketMapper extends BaseMapper<OrderTicket> {

    List<OrderTicket> selectByOrderMobileNo(String mobileNo, Integer enterpriseId);

    int updateFetchStatus(Integer targetStatus, Date fetchTime, List<Integer> ticketIds, List<Integer> oldStatusList, Integer orderId);
}