package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Data
public class SmsMessageDTO {

    private Integer id;

    private String smsId;

    private String productChannelId;

    private String product;

    private String channel;

    private String templateCode;

    private String bizId;

    private String content;

    private String cellphone;

    private String variables;

    private Integer sendState;

    private Integer failCount;

    private String remarks;

    private Integer enterpriseId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;
}
