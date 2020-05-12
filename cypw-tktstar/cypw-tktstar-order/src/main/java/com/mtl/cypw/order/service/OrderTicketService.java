package com.mtl.cypw.order.service;

import com.mtl.cypw.order.model.OrderTicket;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 15:20
 */
public interface OrderTicketService {

    boolean verifyOrderCompleted(Integer ticketId);

    boolean checking(Integer ticketId, Integer checkChannel, Date checkTime);

    boolean checkCompleted(Integer ticketId, Integer checkChannel, Date checkTime);

    OrderTicket findOneById(Integer ticketId);

    OrderTicket findOneByCheckCode(String checkCode, Integer enterpriseId);

    List<OrderTicket> findETicketsByMobileNo(String mobileNo, Integer enterpriseId);

    boolean updateFetchStatus(Integer targetStatus, Date fetchTime, List<Integer> ticketIds, List<Integer> oldStatusList, Integer orderId);

}
