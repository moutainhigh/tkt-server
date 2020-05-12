package com.mtl.cypw.payment.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.payment.pojo.PaymentLog;
import org.apache.ibatis.annotations.Param;

/**
 * @author tang.
 * @date 2020年01月06日 下午06:47:40
 */
public interface PaymentLogMapper extends BaseMapper<PaymentLog> {

    /**
     * 修改支付日志状态
     *
     * @param orderId
     */
    void updateStatus(@Param("orderId") Integer orderId);
}