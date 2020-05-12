package com.mtl.cypw.domain.admin.param;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/3/18.
 */
@Data
public class UserLoginParam {
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 密码
     */
    private String loginPass;
    /**
     * 企业id
     */
    private Integer enterpriseId;

    public Boolean checkParam() {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPass) || enterpriseId == null) {
            return false;
        }
        return true;
    }
}
