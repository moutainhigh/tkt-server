package com.mtl.cypw.domain.wx.response;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class WxConfigResponse {
    private String appId;
    private String nonceStr;
    private long timestamp;
    private String url;
    private String signature;
}
