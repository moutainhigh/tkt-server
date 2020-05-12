package com.mtl.cypw.order.service.impl;

import com.google.common.collect.Lists;
import com.mtl.cypw.domain.order.enums.OrderStatusEnum;
import com.mtl.cypw.domain.ticket.enums.CheckStatusEnum;
import com.mtl.cypw.order.mapper.OrderMapper;
import com.mtl.cypw.order.mapper.OrderTicketMapper;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderTicket;
import com.mtl.cypw.order.service.OrderTicketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-17 20:57
 */
@Slf4j
@Service
public class OrderTicketServiceImpl implements OrderTicketService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderTicketMapper orderTicketMapper;

    @Override
    public boolean verifyOrderCompleted(Integer ticketId) {
        OrderTicket ticket = findOneById(ticketId);
        if (ticket == null) {
            return false;
        }
        Example ticketExample = new Example(OrderTicket.class);
        Example.Criteria ticketExampleCriteria = ticketExample.createCriteria();
        ticketExampleCriteria.andIsNotNull("checkCode");
        ticketExampleCriteria.andEqualTo("orderId", ticket.getOrderId());
        ticketExampleCriteria.andIn("checkStatus", Lists.newArrayList(CheckStatusEnum.NOT_CHECK.getCode(),
                CheckStatusEnum.CHECKING.getCode()));
        List<OrderTicket> tickets = orderTicketMapper.selectByExample(ticketExample);
        if (CollectionUtils.isEmpty(tickets)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checking(Integer ticketId, Integer checkChannel, Date checkTime) {
        OrderTicket ticket = findOneById(ticketId);
        if (ticket != null) {
            ticket.setCheckStatus(CheckStatusEnum.CHECKING.getCode());
            ticket.setCheckChannel(checkChannel);
            ticket.setCheckTime(checkTime);
            orderTicketMapper.updateByPrimaryKeySelective(ticket);
        }
        return true;
    }

    @Override
    public boolean checkCompleted(Integer ticketId, Integer checkChannel, Date checkTime) {
        OrderTicket ticket = findOneById(ticketId);
        if (ticket != null) {
            ticket.setCheckStatus(CheckStatusEnum.COMPLETED.getCode());
            ticket.setCheckChannel(checkChannel);
            ticket.setCheckTime(checkTime);
            orderTicketMapper.updateByPrimaryKeySelective(ticket);
            Order order = orderMapper.selectByPrimaryKey(ticket.getOrderId());
            order.setOrderStatus(OrderStatusEnum.SUCCEED.getCode());
            order.setConsumedTime(checkTime);
            orderMapper.updateByPrimaryKeySelective(order);
        }
        return true;
    }

    @Override
    public OrderTicket findOneById(Integer ticketId) {
        return orderTicketMapper.selectByPrimaryKey(ticketId);
    }

    @Override
    public OrderTicket findOneByCheckCode(String checkCode, Integer enterpriseId) {
        if (StringUtils.isBlank(checkCode) || enterpriseId == null) {
            return null;
        }
        Example ticketExample = new Example(OrderTicket.class);
        Example.Criteria ticketExampleCriteria = ticketExample.createCriteria();
        ticketExampleCriteria.andIsNotNull("checkCode");
        ticketExampleCriteria.andEqualTo("checkCode", checkCode);
        ticketExampleCriteria.andEqualTo("enterpriseId", enterpriseId);
        List<OrderTicket> tickets = orderTicketMapper.selectByExample(ticketExample);
        if (CollectionUtils.isEmpty(tickets)) {
            return null;
        }
        return CollectionUtils.isNotEmpty(tickets) ? tickets.get(0) : null;
    }

    @Override
    public List<OrderTicket> findETicketsByMobileNo(String mobileNo, Integer enterpriseId) {
        if (StringUtils.isBlank(mobileNo) || enterpriseId == null) {
            return Collections.emptyList();
        }
        List<OrderTicket> tickets = orderTicketMapper.selectByOrderMobileNo(mobileNo, enterpriseId);
        if (CollectionUtils.isNotEmpty(tickets)) {
            return tickets;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean updateFetchStatus(Integer targetStatus, Date fetchTime, List<Integer> ticketIds, List<Integer> oldStatusList, Integer orderId) {
        int count = orderTicketMapper.updateFetchStatus(targetStatus, fetchTime, ticketIds, oldStatusList, orderId);
        return count > 0;
    }


}
