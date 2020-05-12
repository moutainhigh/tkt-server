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
public class CheckInTicketParam extends BaseParam {

    /**
     * 电子票ID
     */
    private String ticketId;

    /**
     * 检票码
     */
    private String checkCode;

    /**
     * 检票渠道
     * @see com.mtl.cypw.domain.ticket.enums.CheckChannelEnum
     */
    private Integer checkChannel;

    /**
     * 检票方法
     * @see com.mtl.cypw.domain.ticket.enums.CheckMethodEnum
     */
    private Integer checkMethod;

    /**
     * 检票账号
     */
    private Integer checkUserId;

    /**
     * 检票账号名称
     */
    private String checkUserName;

    /**
     * 检票设备信息
     */
    private CheckInDeviceParam checkInDeviceParam;

    /**
     * 企业标识
     */
    private Integer enterpriseId;


    @Override
    public void checkParam() {

    }
}
