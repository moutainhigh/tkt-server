package com.mtl.cypw.provider.order.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.order.endpoint.OrderApi;
import com.mtl.cypw.domain.order.param.*;
import com.mtl.cypw.provider.order.service.biz.OrderOpEntrance;
import com.mtl.cypw.provider.order.service.uniform.UniformOrderEntrance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@RestController
public class OrderEndpoint implements OrderApi {

    @Resource
    private OrderOpEntrance orderOpEntrance;

    @Resource
    private UniformOrderEntrance uniformOrderEntrance;

    @Override
    public TSingleResult<Integer> create(GenericRequest<UserCreateOrderParam> request) {
        UniformCreateOrderParam param = request.getParam();
        return uniformOrderEntrance.uniformCreateOrder(param);
    }

    @Override
    public TSingleResult<Boolean> paid(GenericRequest<OrderPaidParam> request) {
        OrderPaidParam param = request.getParam();
        return orderOpEntrance.paidOrder(param);
    }

    @Override
    public TSingleResult<Boolean> cancel(GenericRequest<OrderCancelParam> request) {
        OrderCancelParam param = request.getParam();
        return orderOpEntrance.cancelOrder(param);
    }

    @Override
    public TSingleResult<Boolean> consumeById(IdRequest request) {
        return null;
    }

    @Override
    public TSingleResult<Boolean> syncById(IdRequest request) {
        return null;
    }

    @Override
    public TSingleResult<Boolean> deleteById(IdRequest request) {
        return null;
    }

    @Override
    public TSingleResult<Boolean> confirmReceipt(IdRequest request) {
        return orderOpEntrance.confirmReceipt(Integer.parseInt(request.getId()));
    }

    @Override
    public TSingleResult<Boolean> bindExpressNo(GenericRequest<BindExpressNoParam> request) {
        BindExpressNoParam bindExpressNoParam  = request.getParam();
        return orderOpEntrance.bindExpressNo(bindExpressNoParam);
    }


}
