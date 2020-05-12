package com.mtl.cypw.domain.member.param;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class MemberQueryParam {

    private Integer memberId;

    private String memberMobile;

    private Integer enterpriseId;

    private String thirdPartyUserId;
}
