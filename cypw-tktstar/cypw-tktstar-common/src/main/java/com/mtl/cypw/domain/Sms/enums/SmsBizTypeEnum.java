package com.mtl.cypw.domain.Sms.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信发送业务类型
 * @author sq
 * @date 2020/3/9  11:53
 */
@Getter
@AllArgsConstructor
public enum SmsBizTypeEnum {
    /**
     * app端
     */
    SMS_APP("sms_code", "app端"),
    /**
     * 后台管理端
     */
    SMA_BACKEND("sms_backend", "后台管理端");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }


}
