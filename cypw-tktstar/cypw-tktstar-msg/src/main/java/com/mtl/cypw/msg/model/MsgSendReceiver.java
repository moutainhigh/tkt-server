package com.mtl.cypw.msg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年03月05日 下午05:05:58
 */
@Data
@Table(name = "cy_msg_send_receiver")
public class MsgSendReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "send_record_id")
    private Integer sendRecordId;


    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "send_status")
    private String sendStatus;

    @Column(name = "remark")
    private String remark;

    @Column(name = "read_status")
    private Integer readStatus;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}