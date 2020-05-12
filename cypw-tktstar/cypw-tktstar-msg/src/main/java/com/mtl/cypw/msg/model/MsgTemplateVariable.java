package com.mtl.cypw.msg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年03月05日 下午05:05:58
 */
@Data
@Table(name = "cy_msg_template_variable")
public class MsgTemplateVariable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "template_config_id")
    private String templateConfigId;

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "variable_name")
    private String variableName;

    @Column(name = "variable_default_value")
    private String variableDefaultValue;

    @Column(name = "variable_describe")
    private String variableDescribe;

    @Column(name = "ext_1")
    private String ext1;

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