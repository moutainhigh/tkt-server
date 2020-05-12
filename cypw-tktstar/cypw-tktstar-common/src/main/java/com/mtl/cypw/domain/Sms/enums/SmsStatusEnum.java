package com.mtl.cypw.domain.Sms.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sq
 * @date 2020/3/9  12:47
 */
@Getter
@AllArgsConstructor
public enum SmsStatusEnum implements EntityEnum {

    /**
     * 发送失败
     */
    SMA_FAIL(0, "发送失败"),
    /**
     * 发送成功
     */
    SMS_SUCCESS(1, "发送成功"),
    /**
     * 未发送
     */
    SMS_UNSEND(2, "未发送");
    private int code;
    private String name;


}
