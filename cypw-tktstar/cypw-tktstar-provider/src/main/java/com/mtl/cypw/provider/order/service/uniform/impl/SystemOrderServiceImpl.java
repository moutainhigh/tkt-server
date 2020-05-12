package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.domain.order.param.SystemCreateOrderParam;
import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.provider.order.converter.SystemOrderConverter;
import com.mtl.cypw.provider.order.service.uniform.AbstractUniformOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 18:00
 */
@Slf4j
@Component(value = "systemOrderService")
public class SystemOrderServiceImpl extends AbstractUniformOrderService {

    @Autowired
    private SystemOrderConverter converter;

    @Override
    protected void risk(Order order, UniformCreateOrderParam request) {

    }

    @Override
    protected void verify(Order order, UniformCreateOrderParam request) {

    }

    @Override
    protected boolean needConsumeLimit() {
        return true;
    }

    @Override
    protected boolean needTicketMapping() {
        return false;
    }

    @Override
    protected boolean needStartTicketAfterLock() {
        return false;
    }

    @Override
    protected boolean needCreateOrderForMtlCenter() {
        return false;
    }

    @Override
    public Order transform(UniformCreateOrderParam request) {
        Assert.isTrue(request instanceof SystemCreateOrderParam,"SystemCreateOrderParam invalid");
        SystemCreateOrderParam param = (SystemCreateOrderParam) request;
        return converter.convert(param);
    }

    @Override
    public void removeCart(Order order) {

    }
}
