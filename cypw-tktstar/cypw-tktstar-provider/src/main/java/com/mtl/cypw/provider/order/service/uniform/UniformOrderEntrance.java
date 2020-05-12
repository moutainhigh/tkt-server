package com.mtl.cypw.provider.order.service.uniform;

import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:45
 */
public interface UniformOrderEntrance {

    /**
     * 下单创建【线下/线上/渠道】
     * @param request
     * @return
     */
    TSingleResult<Integer> uniformCreateOrder(UniformCreateOrderParam request);

}
