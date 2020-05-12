package com.mtl.cypw.provider.order.service.uniform.impl;

import com.mtl.cypw.domain.order.param.UniformCreateOrderParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.provider.order.service.uniform.AbstractUniformOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 18:00
 */
@Slf4j
@Component(value = "channelOrderService")
public class ChannelOrderServiceImpl extends AbstractUniformOrderService {
    @Override
    protected void risk(Order order, UniformCreateOrderParam request) {

    }

    @Override
    protected void verify(Order order, UniformCreateOrderParam request) {

    }

    @Override
    protected boolean needConsumeLimit() {
        return false;
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
        return null;
    }

    @Override
    public void removeCart(Order order) {

    }
}
