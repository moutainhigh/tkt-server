package com.mtl.cypw.domain.member.param;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Data
public class MemberSignInQueryParam {
    /**
     * 用户id
     */
    private Integer memberId;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 项目id
     */
    private Integer programId;
    /**
     * 场次id
     */
    private Integer eventId;
    /**
     * 剧院id
     */
    private Integer theatreId;
    /**
     * 签到类型
     * 1：正常签到，2：迟到签到，3：异地签到，4：异地迟到签到
     */
    private Integer type;
    /**
     * 商户id
     */
    private Integer enterpriseId;
    /**
     * 签到时间开始
     */
    private Date signInTimeBegin;
    /**
     * 签到时间结束
     */
    private Date signInTimeEnd;
}
