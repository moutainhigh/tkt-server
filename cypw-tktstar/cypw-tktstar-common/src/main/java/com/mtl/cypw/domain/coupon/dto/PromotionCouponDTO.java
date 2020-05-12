package com.mtl.cypw.domain.coupon.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Data
public class PromotionCouponDTO {
    private Integer couponId;

    private Integer promotionId;

    private String couponCode;

    private String couponPass;

    private Integer isUsed;

    private String orderNo;

    private Date useDate;

    private Integer isBinded;

    private Integer memberId;

    private Date bindDate;

    private Integer isEnable;

    private Date addDate;

    private Integer addUser;

    private Date beginDate;

    private Date endDate;

    private String useLog;

    private Integer enterpriseId;
}
