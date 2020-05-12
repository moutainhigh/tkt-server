package com.mtl.cypw.mq.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Message;
import com.mtl.cypw.mq.handler.MessageQueueHandler;
import com.mtl.mq.util.base.BaseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-17 21:30
 */
@Slf4j
@Component
public class TktMessageQueueListener extends BaseMessageListener implements ApplicationContextAware {
    private static Map<String, Class<? extends MessageQueueHandler>> tagHandlerMap = new HashMap<>();

    static {

    }
    private ApplicationContext applicationContext;

    public static Set<String> getTags() {
        Set<String> tags = new HashSet<>();

        return tags;
    }

    @Override
    public Action doConsume(Message message) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
