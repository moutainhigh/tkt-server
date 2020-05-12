package com.mtl.cypw.msg.service;

import com.mtl.cypw.msg.mapper.MsgSendRecordMapper;
import com.mtl.cypw.msg.model.MsgSendRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@Slf4j
@Service
public class MsgSendRecordService {

    @Resource
    private MsgSendRecordMapper mapper;

    public Integer addMsgSendRecord(MsgSendRecord record){
       mapper.insertSelective(record);
       return record.getId();
    }
}
