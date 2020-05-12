package com.mtl.cypw.provider.msg.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.msg.endpoint.MsgSendApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.util.ThreadPoolTaskUtil;
import com.mtl.cypw.domain.msg.enums.MsgTypeEnum;
import com.mtl.cypw.domain.msg.param.MsgSendParam;
import com.mtl.cypw.msg.service.MsgSendReceiverService;
import com.mtl.cypw.msg.service.MsgSendRecordService;
import com.mtl.cypw.provider.msg.converter.MsgConverter;
import com.mtl.cypw.provider.msg.service.SubscribeMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/5.
 */
@Slf4j
@RestController
public class MsgSendEndpoint implements MsgSendApi {

    @Resource
    private MsgSendRecordService msgSendRecordService;

    @Resource
    private MsgSendReceiverService msgSendReceiverService;

    @Resource
    SubscribeMsgService subscribeMsgService;

    @Resource
    MsgConverter msgConverter;

    @Override
    public TSingleResult<Boolean> sendMsg(GenericRequest<MsgSendParam> request) {
        MsgSendParam param = request.getParam();
        if (!param.checkParam()) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        try {
            if (MsgTypeEnum.MINIAPP_SUBSCRIBE.equals(param.getMsgType())) {
                ThreadPoolTaskUtil.execute(() -> subscribeMsgService.miniappSubscribeMsg(param));
            } else {
                Integer sendRecordId = msgSendRecordService.addMsgSendRecord(msgConverter.toMsgSendRecord(param));
                msgSendReceiverService.addMsgSendReceiver(msgConverter.toMsgSendReceiver(param, sendRecordId));
                return ResultBuilder.succTSingle(true);
            }
        } catch (BusinessException e) {
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        }

        return ResultBuilder.succTSingle(true);
    }

}
