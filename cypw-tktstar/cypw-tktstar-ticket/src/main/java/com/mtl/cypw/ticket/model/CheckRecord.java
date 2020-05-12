package com.mtl.cypw.ticket.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-02-13 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_check_record")
public class CheckRecord extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 电子票ID
     */
    @Column(name = "ticket_id")
    private Integer ticketId;

    /**
     * 项目ID
     */
    @Column(name = "program_id")
    private Integer programId;

    /**
     * 项目名称
     */
    @Column(name = "program_name")
    private String programName;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 场次名称
     */
    @Column(name = "event_name")
    private String eventName;

    /**
     * 检票渠道（1-线下运营检票,2-小程序检票,3-PDA检票,4-闸机检票）
     */
    @Column(name = "channel")
    private Integer channel;

    /**
     * 设备类型（1-移动手机,2-手持PDA,3-品牌闸机）
     */
    @Column(name = "device_type")
    private Integer deviceType;

    /**
     * 设备版本信息
     */
    @Column(name = "device_version")
    private String deviceVersion;

    /**
     * 设备唯一码
     */
    @Column(name = "device_unique_code")
    private String deviceUniqueCode;

    /**
     * 检票方式（1-人工核检,2-二维码检票,3-RFID检票,4-身份证入场）
     */
    @Column(name = "check_method")
    private Integer checkMethod;

    /**
     * 验检身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 验检身份证名称
     */
    @Column(name = "id_card_name")
    private String idCardName;

    /**
     * 实名制绑定身份证号
     */
    @Column(name = "binding_id_card")
    private String bindingIdCard;

    /**
     * 实名制绑定身份证名称
     */
    @Column(name = "binding_id_card_name")
    private String bindingIdCardName;

    /**
     * 检票入场时间
     */
    @Column(name = "check_time")
    private Date checkTime;

    /**
     * 电子票检票码
     */
    @Column(name = "check_code")
    private String checkCode;

    /**
     * 检票人ID
     */
    @Column(name = "check_user_id")
    private Integer checkUserId;

    /**
     * 检票人姓名
     */
    @Column(name = "check_user_name")
    private String checkUserName;

    /**
     * 检票入口ID
     */
    @Column(name = "check_entry_id")
    private String checkEntryId;

    /**
     * 检票入口
     */
    @Column(name = "check_entry")
    private String checkEntry;

    /**
     * 票价
     */
    @Column(name = "ticket_price")
    private Long ticketPrice;

    /**
     * 票价描述
     */
    @Column(name = "ticket_price_desc")
    private String ticketPriceDesc;

    /**
     * 场馆描述
     */
    @Column(name = "venue_name")
    private String venueName;

    /**
     * 区域描述
     */
    @Column(name = "zone_name")
    private String zoneName;

    /**
     * 座位描述
     */
    @Column(name = "seat_name")
    private String seatName;

    /**
     * 客户手机
     */
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }
}