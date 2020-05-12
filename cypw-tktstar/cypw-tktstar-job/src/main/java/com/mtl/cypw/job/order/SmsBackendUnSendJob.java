package com.mtl.cypw.job.order;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.juqitech.service.utils.BeanCopierUtils;
import com.mtl.common.jobexecutor.service.jobhandler.AbstractJobHandler;
import com.mtl.cypw.domain.Sms.enums.SmsStatusEnum;
import com.mtl.cypw.mpm.model.SmsMessage;
import com.mtl.cypw.mpm.service.SmsService;
import com.mtl.mtc.notification.endpoint.NotificationSendApi;
import com.mtl.notification.param.RpcSendSmsParam;
import com.mtl.notification.param.SendSmsParam;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author sq
 * @date 2020/3/9  13:39
 */
@Slf4j
@Component
@JobHandler(value = "SmsBackendUnSendJob")
public class SmsBackendUnSendJob extends AbstractJobHandler {
    @Autowired
    private SmsService smsServiceImpl;
    @Resource
    NotificationSendApi notificationSendApi;

    @Override
    protected void doExecute(String bizType) {
        //bizId规则 如 sms_backend:bind_express_no,sms_app:commit 多个服务用,分割
        log.info("job 参数 param:{}", bizType);
        List<String> bizTypeList = Lists.newArrayList(StringUtils.split(bizType,","));
        List<SmsMessage> list = smsServiceImpl.findNoSendSmsByBizTypes(bizTypeList);
        int count = 0;
        if (!CollectionUtils.isEmpty(list)) {
            for (SmsMessage sendSmsParam : list) {
                log.info("短信发送开始>>>>>>,id:{}", sendSmsParam.getId());
                RpcSendSmsParam smsParam = new RpcSendSmsParam();
                BeanCopierUtils.convert(sendSmsParam, smsParam);
                SendSmsParam param = new SendSmsParam();
                param.setSmsId(sendSmsParam.getSmsId());
                param.setCellphone(sendSmsParam.getCellphone());
                param.setContent(sendSmsParam.getContent());
                smsParam.setInfos(Arrays.asList(param));
                GenericRequest<RpcSendSmsParam> request = new GenericRequest<>();
                request.setParam(smsParam);
                log.info("短信发送参数 request:{}",request);
                TSingleResult<Boolean> result = notificationSendApi.sendSms(request);
                if (!result.success()) {
                    log.error("短信发送失败,id:{} result:{}", sendSmsParam.getId(), JSONObject.toJSONString(result));
                    sendSmsParam.setSendState(SmsStatusEnum.SMA_FAIL.getCode());
                    sendSmsParam.setFailCount(sendSmsParam.getFailCount() + 1);
                } else {
                    sendSmsParam.setSendState(SmsStatusEnum.SMS_SUCCESS.getCode());
                    count++;
                }
                smsServiceImpl.updateSendStateAndFailCount(sendSmsParam);

                log.info("短信发送结束<<<<<<,id:{}", sendSmsParam.getId());
            }
        }
        log.info("短信发送成功个数为:{}", count);

    }
}
