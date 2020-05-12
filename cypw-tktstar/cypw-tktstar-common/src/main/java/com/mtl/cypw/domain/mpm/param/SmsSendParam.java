package com.mtl.cypw.domain.mpm.param;

import lombok.Data;

import java.util.Map;

/**
 * @author tang.
 * @date 2019/12/19.
 */
@Data
public class SmsSendParam {
    private Integer smsId;
    private String productChannelId;
    private String product;
    private String channel;
    private String templateCode;
    private String bizId;
    private String content;
    private Integer countryCode;
    private String cellphone;
    private Map<String, String> variables;
    private Integer enterpriseId;

}
