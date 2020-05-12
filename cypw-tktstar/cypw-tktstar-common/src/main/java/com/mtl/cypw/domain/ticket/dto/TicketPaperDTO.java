package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 22:14
 */
@Setter
@Getter
@ToString(callSuper = true)
public class TicketPaperDTO extends BaseEntityInfo {

    /**
     * 是否验证通过
     */
    private Boolean passed;

    /**
     * 验证提示消息
     */
    private String passMessage;

    /**
     * 电子票ID
     */
    private Integer ticketId;

    /**
     * 项目ID
     */
    private Integer programId;

    /**
     * 项目名称
     */
    private String programName;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 场次名称
     */
    private String eventName;

    /**
     * 检票渠道（1-线下运营检票,2-小程序检票,3-PDA检票,4-闸机检票）
     */
    private Integer channel;

    /**
     * 设备类型（1-移动手机,2-手持PDA,3-品牌闸机）
     */
    private Integer deviceType;

    /**
     * 设备版本信息
     */
    private String deviceVersion;

    /**
     * 设备唯一码
     */
    private String deviceUniqueCode;

    /**
     * 检票方式（1-人工核检,2-二维码检票,3-RFID检票,4-身份证入场）
     */
    private Integer checkMethod;

    /**
     * 验检身份证号
     */
    private String idCard;

    /**
     * 验检身份证名称
     */
    private String idCardName;

    /**
     * 实名制绑定身份证号
     */
    private String bindingIdCard;

    /**
     * 实名制绑定身份证名称
     */
    private String bindingIdCardName;

    /**
     * 检票入场时间
     */
    private Date checkTime;

    /**
     * 电子票检票码
     */
    private String checkCode;

    /**
     * 检票人ID
     */
    private Integer checkUserId;

    /**
     * 检票人姓名
     */
    private String checkUserName;

    /**
     * 检票入口
     */
    private String checkEntry;

    /**
     * 已检票次数
     */
    private Integer checkCount;

    /**
     * 票价
     */
    private BigDecimal ticketPrice;

    /**
     * 票价描述
     */
    private String ticketPriceDesc;

    /**
     * 场馆描述
     */
    private String venueName;

    /**
     * 区域描述
     */
    private String zoneName;

    /**
     * 座位描述
     */
    private String seatName;

    /**
     * 客户手机
     */
    private String mobileNo;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

}
