package com.mtl.cypw.domain.mpm.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Data
public class SmsMessageQueryParam {

    private String product;

    private String cellphone;

    private Integer sendState;

    private Integer enterpriseId;

    private Integer LessFailCount;

    private Integer greaterFailCount;

    private List<String> bizTypes;
}
