package com.mtl.cypw.domain.wx.request;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class WxRequest {

    /**
     * 商户id
     */
    private Integer enterpriseId;

    /**
     * 分享的url
     */
    private String url;

    /**
     * 换取openid的code
     */
    private String code;

}
