package com.mtl.cypw.msg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年03月05日 下午05:05:58
 */
@Data
@Table(name = "cy_msg_template_config")
public class MsgTemplateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "template_type")
    private String templateType;

    @Column(name = "msg_code")
    private String msgCode;

    @Column(name = "msg_type")
    private String msgType;

    @Column(name = "page_url")
    private String pageUrl;

    @Column(name = "miniapp_state")
    private String miniappState;

    @Column(name = "remark")
    private String remark;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}