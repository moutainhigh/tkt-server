package com.mtl.cypw.test;

import com.mtl.cypw.domain.msg.enums.MsgTypeEnum;
import com.mtl.cypw.domain.msg.param.MsgSendParam;
import com.mtl.cypw.domain.payment.config.WxConfigure;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.msg.service.SubscribeMsgService;
import com.mtl.cypw.wx.miniapp.MiniappService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Slf4j
public class MiniappTest extends BaseTest {

    @Resource
    SubscribeMsgService subscribeMsgService;

    @Resource
    MiniappService miniappService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Test
    public void sendMessage() {
        MsgSendParam.ReceiverUserInfo receiverUserInfo = new MsgSendParam.ReceiverUserInfo();
        receiverUserInfo.setMemberId(1);
        receiverUserInfo.setOpenId("o_Vqq5S-5UR3LzC6uRBYgxaGseGI");
        MsgSendParam param = new MsgSendParam();
        param.setEnterpriseId(10);
        param.setMsgCode("PROGRAM_START");
        param.setMsgType(MsgTypeEnum.MINIAPP_SUBSCRIBE);
        param.addReceiverUserInfo(receiverUserInfo);
        Map<String, String> variables = new HashMap<>();
        variables.put("programName", "皇家赌场");
        variables.put("theatreName", "上海大剧院");
        variables.put("showTime", "2020-03-08 15:00:00");
        variables.put("seat", "05排08坐");
        variables.put("ticketCode", "088077066");
        param.setVariables(variables);
        subscribeMsgService.miniappSubscribeMsg(param);
    }

    @Test
    public void getJscode2Session() {
        String code = "071OoxaY0LY02X12ep8Y0AxIaY0OoxaU";
        WxConfigure configure = enterpriseConfigService.getWxConfigure(10);
        miniappService.getJscode2Session(code, configure);
    }

}
