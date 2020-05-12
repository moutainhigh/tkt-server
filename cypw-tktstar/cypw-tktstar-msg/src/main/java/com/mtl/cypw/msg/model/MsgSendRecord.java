package com.mtl.cypw.msg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年03月05日 下午05:05:58
 */
@Data
@Table(name = "cy_msg_send_record")
public class MsgSendRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "msg_code")
    private String msgCode;

    @Column(name = "msg_type")
    private String msgType;

    @Column(name = "send_user_id")
    private Integer sendUserId;

    @Column(name = "ref_id")
    private Integer refId;

    @Column(name = "content")
    private String content;

    @Column(name = "send_type")
    private String sendType;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}