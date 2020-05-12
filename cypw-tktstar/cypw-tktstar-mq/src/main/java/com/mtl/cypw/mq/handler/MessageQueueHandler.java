package com.mtl.cypw.mq.handler;

import com.aliyun.openservices.ons.api.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtl.cypw.mq.inf.TktMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-17 21:36
 */
@Slf4j
public abstract class MessageQueueHandler<T extends TktMessage> implements IRollbackMessageHandler {

    private final Class<T> messageClass;

    public MessageQueueHandler(Class<T> cls) {
        messageClass = cls;
        if (cls == null) {
            log.error("MessageQueueHandler cls is empty.");
            throw new NullPointerException("cls must not empty.");
        }
    }

    @Override
    public void handle(Message message) {
        byte[] bytes = message.getBody();
        try {
            T tktMessage = new ObjectMapper().readValue(new String(bytes), messageClass);
            String uid = message.getUserProperties("uid");
            tktMessage.setMsgId(message.getMsgID());
            tktMessage.setUid(uid);
            process(tktMessage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public abstract void process(T message);
}
