package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月30日 下午04:23:35
 */
@Data
@Table(name = "cy_sms_message")
public class SmsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sms_id")
    private String smsId;

    @Column(name = "product_channel_id")
    private String productChannelId;

    @Column(name = "product")
    private String product;

    @Column(name = "channel")
    private String channel;

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "biz_id")
    private String bizId;
    @Column(name = "biz_type")
    private String bizType;

    @Column(name = "content")
    private String content;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "variables")
    private String variables;

    @Column(name = "send_state")
    private Integer sendState;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Integer deleted;
}