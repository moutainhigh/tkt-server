package com.mtl.cypw.order.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.order.model.OrderDelivery;
import org.apache.ibatis.annotations.Param;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
public interface OrderDeliveryMapper extends BaseMapper<OrderDelivery> {

    int updateFetchStatus(@Param("targetStatus") Integer targetStatus, @Param("fetchTime") java.util.Date fetchTime, @Param("orderId") Integer orderId,  @Param("oldStatus") Integer oldStatus);

    /**
     * 绑定物流编号
     *
     * @param orderDelivery 实体类参数
     * @param sourceStatus  原始物流状态
     * @param targetStatus  改变物流状态
     * @return 更新结果数
     */
    int updateExpressNo(@Param("orderDelivery") OrderDelivery orderDelivery, @Param("sourceStatus") int sourceStatus, @Param("targetStatus") int targetStatus);
}