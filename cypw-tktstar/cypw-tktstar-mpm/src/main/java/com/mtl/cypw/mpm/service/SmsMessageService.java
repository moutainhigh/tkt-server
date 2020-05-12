package com.mtl.cypw.mpm.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import com.mtl.cypw.mpm.model.SmsMessage;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/30.
 */
public interface SmsMessageService {
    /**
     * 添加短信发送记录
     * @param smsMessage
     */
    void addSmsMessage(SmsMessage smsMessage);

    /**
     * 修改发送失败次数
     * @param id
     */
    void updateFailCount(Integer id);

    /**
     * 查询单条发送记录
     * @param id
     * @return
     */
    SmsMessage getSmsMessage(Integer id);

    /**
     * 查询发送短信列表
     * @param queryParam
     * @return
     */
    List<SmsMessage> searchSmsMessage(SmsMessageQueryParam queryParam);

    /**
     * 分页查询发送短信列表
     * @param queryParam
     * @return
     */
    List<SmsMessage> searchSmsMessagePage(SmsMessageQueryParam queryParam, Pagination pagination);

    /**
     * 更新短信信息发送状态和失败次数
     * @param sendSmsParam 参数
     * @return 更新个数
     */
    int updateSendStateAndFailCount(SmsMessage sendSmsParam);

    /**
     * 查询后台服务未发送的短信列表
     * @param queryParam 参数
     * @return 信息列表
     */
    List<SmsMessage> findNoSendSmsByBizTypes(SmsMessageQueryParam queryParam);
}
