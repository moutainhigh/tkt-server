package com.mtl.cypw.mpm.service;

import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.cypw.mpm.model.SmsMessage;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/16.
 */
public interface SmsService {

    void sendMQSms(SmsSendParam param);

    /**
     * 发送消息
     * @param param
     * @return
     */
    TSingleResult<Boolean> sendSms(SmsSendParam param);

    /**
     * 查询后台服务未发送的短信列表
     * @param bizTypes bizId集合 bizId = SmsBizTypeEnum.SMA_BACKEND.getCode():SmsBizTemplate
     * @return SmsMessage集合
     */
    List<SmsMessage> findNoSendSmsByBizTypes(List<String> bizTypes);

    /**
     * 更新短信信息发送状态和失败次数
     * @param sendSmsParam 参数
     * @return 更新个数
     */
    int updateSendStateAndFailCount(SmsMessage sendSmsParam);
}
