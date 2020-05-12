package com.mtl.cypw.service.config;

import com.mtl.cypw.mq.listener.TktMessageQueueListener;
import com.mtl.mq.util.config.MQConfiguration;
import com.mtl.mq.util.consume.CommonMQConsumer;
import com.mtl.mq.util.publish.CommonMQPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MQConfig {

    @Autowired
    private TktMessageQueueListener tktMessageQueueListener;

    /**
     * 普通消息发送者
     */
    @Bean
    public CommonMQPublisher commonMQPublisher(MQConfiguration mqConfiguration) throws Exception {
        return new CommonMQPublisher(mqConfiguration);
    }

    /**
     * 普通消息消费者
     */
    @Bean
    public CommonMQConsumer commonMQConsumer(MQConfiguration mqConfiguration) throws Exception {
        return new CommonMQConsumer(mqConfiguration, tktMessageQueueListener, mqConfiguration.getTopic(), TktMessageQueueListener.getTags());
    }


    @Bean
    @Primary
    public MQConfiguration getConfiguration(MQConfigProperties mqConfigProperties) throws Exception {
        return new MQConfiguration(mqConfigProperties.getAccessKey(), mqConfigProperties.getSecretKey(), mqConfigProperties.getMessageType(), mqConfigProperties.getGroupId(), mqConfigProperties.getTopic());
    }

}
