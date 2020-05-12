package com.mtl.cypw.service.config;

import com.mtl.mq.util.config.MQConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("mq.config")
public class MQConfigProperties {

    /**
     * 阿里云访问Key
     */
    private String accessKey;

    /**
     * 阿里云访问安全码
     */
    private String secretKey;

    /**
     * 消息类型：
     * NORMAL - 普通消息；
     * ORDER - 顺序消息
     * TRANSACTION - 事务消息
     */
    private String messageType = MQConfiguration.MessageType.NORMAL.name();

    /**
     * 普通消息生成者ID
     */
    private String producerId;

    /**
     * 消息消费者Id
     */
    private String consumerId;

    /**
     * 消息主题的前缀
     */
    private String topicPrefix = "";

    private String groupId = "";

    private String groupIdLow = "";

    /**
     * 消费者前缀
     */

    private String consumerPrefix = "";
    /**
     * 生产者前缀
     */

    private String producerPrefix = "";

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息主题: 低优先级消息
     */
    private String topicLow;

    public String getTopicWithPrefix() {
        return topicPrefix + topic;
    }

}
