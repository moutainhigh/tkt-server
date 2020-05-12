package com.mtl.cypw.msg.service;

import com.mtl.cypw.msg.mapper.MsgSendReceiverMapper;
import com.mtl.cypw.msg.model.MsgSendReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@Slf4j
@Service
public class MsgSendReceiverService {

    @Resource
    private MsgSendReceiverMapper mapper;

    public void addMsgSendReceiver(MsgSendReceiver msgSendReceiver){
        mapper.insertSelective(msgSendReceiver);
    }

    public void addMsgSendReceiver(List<MsgSendReceiver> msgSendReceiverList){
        mapper.insertList(msgSendReceiverList);
    }
}
