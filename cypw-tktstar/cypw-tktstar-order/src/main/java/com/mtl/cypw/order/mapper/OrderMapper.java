package com.mtl.cypw.order.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.order.model.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 确认 收货
     * @param orderId 订单id
     * @param sourceStatus 原始状态
     * @param targetStatus 目标状态
     * @return 更新个数
     */
    int updateOrderStatusByConfirm(@Param("id") int orderId, @Param("sourceStatus") int sourceStatus, @Param("targetStatus") int targetStatus);
}