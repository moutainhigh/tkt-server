package com.mtl.cypw.domain.auth.response;

import com.mtl.cypw.domain.auth.response.data.CmbAuthInfo;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/14.
 */
@Data
public class CmbAuthResponse {
    /**
     * 商户号
     */
    private String merchantNo;

    private CmbAuthInfo authInfo;

    private Boolean reAuth;
}
