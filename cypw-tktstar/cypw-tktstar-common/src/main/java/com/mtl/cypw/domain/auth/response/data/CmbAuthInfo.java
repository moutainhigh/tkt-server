package com.mtl.cypw.domain.auth.response.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/14.
 */
@Data
public class CmbAuthInfo {

    /**
     * 服务器当前时间，格式为yyyyMMddHHmmss
     */
    private String timestamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
}
