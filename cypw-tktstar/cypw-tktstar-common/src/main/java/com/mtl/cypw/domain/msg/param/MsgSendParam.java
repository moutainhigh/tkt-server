package com.mtl.cypw.domain.msg.param;

import com.mtl.cypw.domain.msg.enums.MsgTypeEnum;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tang.
 * @date 2020/3/5.
 */
@Data
public class MsgSendParam {

    /**
     * 消息code,可用来获取相应模板
     */
    private String msgCode;

    /**
     * 消息类型
     *
     * @see MsgTypeEnum
     */
    private MsgTypeEnum msgType;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 填充数据
     */
    private Map<String, String> variables;

    /**
     * 接收消息的用户
     */
    private List<ReceiverUserInfo> receiverUserInfoList;

    public MsgSendParam addReceiverUserInfo(MsgSendParam.ReceiverUserInfo receiverUserInfo) {
        if (this.receiverUserInfoList == null) {
            this.receiverUserInfoList = new ArrayList();
        }
        this.receiverUserInfoList.add(receiverUserInfo);
        return this;
    }

    @Data
    public static class ReceiverUserInfo {
        /**
         * 用户id（接收消息）
         */
        private Integer memberId;

        /**
         * 用户手机号
         */
        private String phone;

        /**
         * 发送微信类消息时需要openId
         */
        private String openId;
    }

    public Boolean checkParam() {
        if (enterpriseId == null || msgCode == null || msgType == null || CollectionUtils.isEmpty(receiverUserInfoList)) {
            return false;
        }
        return true;
    }
}
