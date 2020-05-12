package com.mtl.cypw.domain.ticket.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 19:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckInDeviceParam extends BaseParam {

    /**
     * 检票设备版本
     */
    private String deviceVersion;

    /**
     * 检票设备类型
     * @see com.mtl.cypw.domain.ticket.enums.DeviceTypeEnum
     */
    private Integer deviceType;

    /**
     * 检票设备唯一标识
     */
    private String deviceUniqueCode;

    /**
     * 验刷身份证号（入场）
     */
    private String idCard;

    /**
     * 验刷身份证名（入场）
     */
    private String idCardName;

    /**
     * 实名制绑定身份证号
     */
    private String bindingIdCard;

    /**
     * 实名制绑定身份证名
     */
    private String bindingIdCardName;

    @Override
    public void checkParam() {

    }
}
