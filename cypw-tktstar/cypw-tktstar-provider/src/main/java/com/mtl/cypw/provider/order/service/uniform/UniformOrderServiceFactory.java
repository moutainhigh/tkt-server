package com.mtl.cypw.provider.order.service.uniform;

import com.mtl.cypw.domain.order.enums.ChannelEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:45
 */
@Component
public class UniformOrderServiceFactory {

    @Resource(name = "userOrderService")
    private UniformOrderService userOrderService;
    @Resource(name = "systemOrderService")
    private UniformOrderService systemOrderService;
    @Resource(name = "channelOrderService")
    private UniformOrderService channelOrderService;


    public UniformOrderService selector(ChannelEnum channel) {
        if (ChannelEnum.BACKEND.getCode() == channel.getCode()) {
            return systemOrderService;
        }
        if (ChannelEnum.CHANNEL.getCode() == channel.getCode()) {
            return channelOrderService;
        }
        return userOrderService;
    }
}
