package com.mtl.cypw.payment.pojo;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/18.
 */
@Data
public class CmbPayBase {
    private String version = "1.0";
    private String charset = "UTF-8";
    private String sign;
    private String signType;
}
