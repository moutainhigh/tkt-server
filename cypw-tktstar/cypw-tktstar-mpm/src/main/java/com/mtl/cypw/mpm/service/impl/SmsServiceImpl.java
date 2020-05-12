package com.mtl.cypw.mpm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.juqitech.service.utils.CommonUtil;
import com.mtl.cypw.domain.Sms.enums.SmsStatusEnum;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.cypw.mpm.model.SmsMessage;
import com.mtl.cypw.mpm.service.SmsMessageService;
import com.mtl.cypw.mpm.service.SmsService;
import com.mtl.mq.util.publish.CommonMQPublisher;
import com.mtl.mtc.notification.NotificationTopicUtils;
import com.mtl.mtc.notification.SmsNotificationSender;
import com.mtl.mtc.notification.endpoint.NotificationSendApi;
import com.mtl.notification.message.SendSmsNotificationMessage;
import com.mtl.notification.param.RpcSendSmsParam;
import com.mtl.notification.param.SendSmsParam;
import com.mtl.notification.util.CellphoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    CommonMQPublisher commonMQPublisher;

    @Resource
    NotificationSendApi notificationSendApi;

    @Resource
    SmsMessageService smsMessageServiceImpl;

    @Override
    public void sendMQSms(SmsSendParam param) {

        try {
            SendSmsNotificationMessage message = new SendSmsNotificationMessage(NotificationTopicUtils.getNotificationTopic());
            message.setBizId(param.getBizId());
            message.setTemplateCode(param.getTemplateCode());
            message.setProduct(param.getProduct());
            message.setChannel("");
            SendSmsParam sendSmsParam = new SendSmsParam();
            sendSmsParam.setSmsId(param.getSmsId() != null ? param.getSmsId().toString() : CommonUtil.generateOID());
            sendSmsParam.setCellphone(CellphoneUtils.getCellPhoneStr(param.getCountryCode(), param.getCellphone()));
            sendSmsParam.setContent(param.getContent());
            message.setInfos(Arrays.asList(sendSmsParam));
            message.setOperatorId(SmsNotificationSender.OPERATOR_SYS);
            message.setVariables(param.getVariables());
            message.setProductChannelId(param.getProductChannelId());
            commonMQPublisher.sendAsyncMessage(message);
        } catch (Exception e) {
            log.error("短信发送异常：{}", e.getMessage());
        }
    }

    @Override
    public TSingleResult<Boolean> sendSms(SmsSendParam param) {
        SendSmsParam sendSmsParam = new SendSmsParam();
        sendSmsParam.setSmsId(param.getSmsId() != null ? param.getSmsId().toString() : CommonUtil.generateOID());
        sendSmsParam.setCellphone(CellphoneUtils.getCellPhoneStr(param.getCountryCode(), param.getCellphone()));
        sendSmsParam.setContent(param.getContent());

        RpcSendSmsParam smsParam = new RpcSendSmsParam();
        smsParam.setBizId(param.getBizId());
        smsParam.setTemplateCode(param.getTemplateCode());
        smsParam.setProduct(param.getProduct());
        smsParam.setChannel("");
        smsParam.setProductChannelId(param.getProductChannelId());
        smsParam.setOperatorId(SmsNotificationSender.OPERATOR_SYS);
        smsParam.setInfos(Arrays.asList(sendSmsParam));
        smsParam.setVariables(param.getVariables());
//            smsParam.setEventId();
//            smsParam.setSource();
        smsParam.setContent(param.getContent());
        GenericRequest<RpcSendSmsParam> request = new GenericRequest<>();
        request.setParam(smsParam);
        TSingleResult<Boolean> result = notificationSendApi.sendSms(request);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setSmsId(sendSmsParam.getSmsId());
        smsMessage.setProductChannelId(param.getProductChannelId());
        smsMessage.setProduct(param.getProduct());
        smsMessage.setChannel(param.getChannel());
        smsMessage.setTemplateCode(param.getTemplateCode());
        smsMessage.setBizId(param.getBizId());
        smsMessage.setContent(param.getContent());
        smsMessage.setCellphone(param.getCellphone());
        if (MapUtils.isNotEmpty(param.getVariables())) {
            smsMessage.setVariables(JSONObject.toJSONString(param.getVariables()));
        }
        smsMessage.setSendState(1);
        smsMessage.setFailCount(0);
        smsMessage.setEnterpriseId(param.getEnterpriseId());
        if (!result.success()) {
            log.error("短信发送失败，result：{}", JSONObject.toJSONString(result));
            smsMessage.setSendState(0);
            smsMessage.setFailCount(1);
        }
        smsMessageServiceImpl.addSmsMessage(smsMessage);
        return result;
    }

    /**
     * 查询后台服务未发送的短信列表
     * @param bizTypes 服务bizId = SmsBizTypeEnum.SMA_BACKEND.getCode():SmsBizTemplate
     * @return SmsMessage集合
     */
    @Override
    public List<SmsMessage> findNoSendSmsByBizTypes(List<String> bizTypes) {
        SmsMessageQueryParam queryParam = new SmsMessageQueryParam();
        queryParam.setBizTypes(bizTypes);
        queryParam.setSendState(SmsStatusEnum.SMS_UNSEND.getCode());
        return smsMessageServiceImpl.findNoSendSmsByBizTypes(queryParam);
    }

    /**
     * 更新短信信息发送状态和失败次数
     * @param sendSmsParam 参数
     * @return 更新个数
     */
    @Override
    public int updateSendStateAndFailCount(SmsMessage sendSmsParam) {
        return smsMessageServiceImpl.updateSendStateAndFailCount(sendSmsParam);
    }
}
