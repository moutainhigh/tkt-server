package com.mtl.cypw.common.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 14:55
 */
@Configuration
@Getter
public class CypwApolloConfig  {

    /**
     * 自营券码批次长度
     */
    @Value("${global.coderepo.bitLength}")
    private Integer bitLength = 9;

    /**
     * 普通订单超时时间
     */
    @Value("${global.payment.timeout.minutes}")
    private Integer paymentTimeout = 15;

    /**
     * 单次购票限制数量
     */
    @Value("${global.buy.limit.quantity}")
    private Integer buyLimitQuantity = 500;

}
