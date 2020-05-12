package com.mtl.cypw.mq.inf;

import com.juqitech.request.Request;
import com.mtl.mq.util.entity.EnvelopedObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-17 21:37
 */
@Getter
@Setter
@ToString
public class TktMessage extends EnvelopedObject implements Request {

    private String msgId;

    private String uid;

    public TktMessage(String topic, String tag) {
        super(topic, tag);
    }

    @Override
    public void checkParam() {

    }
}
