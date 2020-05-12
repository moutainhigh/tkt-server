package com.mtl.cypw.mq.handler;

import com.aliyun.openservices.ons.api.Message;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-17 21:30
 */
public interface IRollbackMessageHandler {

    void handle(Message message);
}
