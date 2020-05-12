package com.mtl.cypw.provider.msg.service;

import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.msg.param.MsgSendParam;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.domain.wx.param.MiniappSubscribeMessageParam;
import com.mtl.cypw.msg.model.MsgSendReceiver;
import com.mtl.cypw.msg.model.MsgTemplateConfig;
import com.mtl.cypw.msg.model.MsgTemplateVariable;
import com.mtl.cypw.msg.service.MsgSendReceiverService;
import com.mtl.cypw.msg.service.MsgSendRecordService;
import com.mtl.cypw.msg.service.MsgTemplateConfigService;
import com.mtl.cypw.msg.service.MsgTemplateVariableService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.msg.converter.MsgConverter;
import com.mtl.cypw.wx.miniapp.MiniappService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@Slf4j
@Service
public class SubscribeMsgService {

    @Resource
    private MsgTemplateConfigService msgTemplateConfigService;

    @Resource
    private MsgTemplateVariableService msgTemplateVariableService;

    @Resource
    private MsgSendRecordService msgSendRecordService;

    @Resource
    private MsgSendReceiverService msgSendReceiverService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    MiniappService miniappService;

    @Resource
    MsgConverter msgConverter;

    public Boolean miniappSubscribeMsg(MsgSendParam param) throws BusinessException {
        MsgTemplateConfig msgTemplateConfig = msgTemplateConfigService.getMsgTemplateConfig(param);
        if (msgTemplateConfig != null) {
            MiniappSubscribeMessageParam subscribeMessageParam = new MiniappSubscribeMessageParam();
            List<MsgTemplateVariable> variables = msgTemplateVariableService.getMsgTemplateVariable(msgTemplateConfig.getId());
            if (CollectionUtils.isNotEmpty(variables)) {
                variables.forEach(n -> {
                    if (StringUtils.isNotEmpty(n.getVariableDefaultValue())) {
                        char symbol = n.getVariableDefaultValue().charAt(0);
                        if ("$".equals(symbol) && param.getVariables() != null) {
                            String key = n.getVariableDefaultValue().substring(1);
                            subscribeMessageParam.addData(new MiniappSubscribeMessageParam.Data(key, param.getVariables().get(key)));
                        } else {
                            subscribeMessageParam.addData(new MiniappSubscribeMessageParam.Data(n.getVariableName(), n.getVariableDefaultValue()));
                        }
                    }
                });
            }
            Integer sendRecordId = msgSendRecordService.addMsgSendRecord(msgConverter.toMsgSendRecord(param));
            WxConfigure configure = enterpriseConfigService.getWxConfigure(param.getEnterpriseId());
            subscribeMessageParam.setTemplateId(msgTemplateConfig.getTemplateCode());
            subscribeMessageParam.setPage(msgTemplateConfig.getPageUrl());
            subscribeMessageParam.setMiniprogramState(msgTemplateConfig.getMiniappState());
            List<MsgSendParam.ReceiverUserInfo> receiverUserInfoList = new ArrayList<>();
            receiverUserInfoList.addAll(param.getReceiverUserInfoList());
            receiverUserInfoList.forEach(n -> {
                try {
                    subscribeMessageParam.setToUser(n.getOpenId());
                    miniappService.sendSubscribeMsg(subscribeMessageParam, configure);
                } catch (BusinessException e) {
                    MsgSendReceiver receiver = new MsgSendReceiver();
                    receiver.setSendRecordId(sendRecordId);
                    receiver.setMemberId(n.getMemberId());
                    receiver.setPhone(n.getPhone());
                    receiver.setOpenId(n.getOpenId());
                    receiver.setSendStatus("FAIL");
                    receiver.setRemark(e.getCode() + "," + e.getMessage());
                    receiver.setEnterpriseId(param.getEnterpriseId());
                    msgSendReceiverService.addMsgSendReceiver(receiver);
                    param.getReceiverUserInfoList().remove(n);
                }
            });
            if (CollectionUtils.isNotEmpty(param.getReceiverUserInfoList())) {
                msgSendReceiverService.addMsgSendReceiver(msgConverter.toMsgSendReceiver(param, sendRecordId));
            }
        } else {
            log.error("未找到消息模板");
            throw new BusinessException(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getMsg(), ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode());
        }
        return true;
    }
}
