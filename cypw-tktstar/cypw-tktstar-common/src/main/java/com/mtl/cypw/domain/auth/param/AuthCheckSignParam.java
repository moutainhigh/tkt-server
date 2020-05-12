package com.mtl.cypw.domain.auth.param;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/1/16.
 */
@Data
public class AuthCheckSignParam {

    private Integer enterpriseId;
    /**
     * 回调数据
     */
    private String responseStr;

    public Boolean checkParam() {
        if (StringUtils.isEmpty(responseStr) || enterpriseId == null) {
            return false;
        }
        return true;
    }
}
