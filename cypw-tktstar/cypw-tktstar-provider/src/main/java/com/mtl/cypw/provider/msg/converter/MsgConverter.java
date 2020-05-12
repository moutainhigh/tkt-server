package com.mtl.cypw.provider.msg.converter;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.msg.param.MsgSendParam;
import com.mtl.cypw.msg.model.MsgSendReceiver;
import com.mtl.cypw.msg.model.MsgSendRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@Component
public class MsgConverter {

    public MsgSendRecord toMsgSendRecord(MsgSendParam param) {
        MsgSendRecord record = new MsgSendRecord();
        record.setMsgCode(param.getMsgCode());
        record.setSendType(param.getMsgType().name());
        record.setContent(JSONObject.toJSONString(param.getVariables()));
        record.setEnterpriseId(param.getEnterpriseId());
        return record;
    }

    public List<MsgSendReceiver> toMsgSendReceiver(MsgSendParam param, Integer sendRecordId) {
        List<MsgSendReceiver> msgSendReceiverList = new ArrayList<>();
        param.getReceiverUserInfoList().forEach(n -> {
            MsgSendReceiver receiver = new MsgSendReceiver();
            receiver.setSendRecordId(sendRecordId);
            receiver.setMemberId(n.getMemberId());
            receiver.setPhone(n.getPhone());
            receiver.setOpenId(n.getOpenId());
            receiver.setSendStatus("SUCCESS");
            receiver.setEnterpriseId(param.getEnterpriseId());
            receiver.setDeleted(0);
            msgSendReceiverList.add(receiver);
        });
        return msgSendReceiverList;
    }

}
