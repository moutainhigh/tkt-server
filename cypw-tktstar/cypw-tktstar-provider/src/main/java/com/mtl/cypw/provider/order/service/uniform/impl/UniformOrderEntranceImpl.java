package com.mtl.cypw.provider.order.service.uniform.impl;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.order.exception.OrderBizException;
import com.mtl.cypw.order.exception.init.OrderInitException;
import com.mtl.cypw.order.exception.lock.OrderLockException;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.provider.order.service.uniform.UniformOrderEntrance;
import com.mtl.cypw.provider.order.service.uniform.UniformOrderService;
import com.mtl.cypw.provider.order.service.uniform.UniformOrderServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:59
 */
@Slf4j
@Component
public class UniformOrderEntranceImpl implements UniformOrderEntrance {

    @Autowired
    private UniformOrderServiceFactory uniformOrderServiceFactory;

    @Override
    public TSingleResult<Integer> uniformCreateOrder(UniformCreateOrderParam request) {
        log.info("Uniform order Creator, request => {}", JsonUtils.toJson(request));
        Assert.notNull(request, "create request is null");
        Order order = null;
        try {
            UniformOrderService uniformOrderService = uniformOrderServiceFactory.selector(request.getChannel());
            order = uniformOrderService.transform(request);
            order = uniformOrderService.init(order, request);
            order = uniformOrderService.lock(order);
            uniformOrderService.removeCart(order);
        } catch (OrderInitException e) {
            log.error("Failed to init order, errorCode = {}, errorMessage = {}", e.getCode(), e.getMessage(), e);
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (OrderLockException e) {
            log.error("Failed to lock order, errorCode = {}, errorMessage = {}", e.getCode(), e.getMessage(), e);
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (OrderBizException e) {
            log.error("Failed to create order, errorCode = {}, errorMessage = {}", e.getCode(), e.getMessage(), e);
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("Unknown exception while create order, errorMessage = {}", e.getMessage(), e);
            return ResultBuilder.failTSingle(ErrorCode.ERROR_ORDER.getCode(), ErrorCode.ERROR_ORDER.getMsg());
        }
        return ResultBuilder.succTSingle(order.getId());
    }

}
