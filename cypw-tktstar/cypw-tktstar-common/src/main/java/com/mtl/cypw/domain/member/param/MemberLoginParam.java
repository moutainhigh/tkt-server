package com.mtl.cypw.domain.member.param;

import com.mtl.cypw.domain.mpm.enums.LoginTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/14.
 */
@Data
public class MemberLoginParam {

    private Integer enterpriseId;
    /**
     * 手机号
     */
    private String memberMobile;

    private String openId;
    /**
     * 第三方登录ID
     */
    private String thirdPartyUserId;
    /**
     * 登录类型
     */
    private LoginTypeEnum loginType;
}
