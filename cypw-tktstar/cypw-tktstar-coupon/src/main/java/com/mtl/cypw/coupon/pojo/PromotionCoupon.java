package com.mtl.cypw.coupon.pojo;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.domain.coupon.enums.CouponStateEnum;
import com.mtl.cypw.common.util.DateTimeUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tang. 
 * @date 2019年12月04日 上午11:15:46
*/
@Data
@Table(name = "tblpromotioncoupon")
public class PromotionCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer couponId;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "coupon_pass")
    private String couponPass;

    @Column(name = "is_used")
    private Integer isUsed;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "use_date")
    private Date useDate;

    @Column(name = "is_binded")
    private Integer isBinded;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "bind_date")
    private Date bindDate;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "use_log")
    private String useLog;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    public boolean isUnUsed() {
        return CouponStateEnum.NOT_USED.getCode() == isUsed;
    }

    public boolean isOwn(Integer memberId) {
        return CommonStateEnum.VALID.getCode() == isBinded
                && this.memberId != null
                && this.memberId.equals(memberId);
    }

    public boolean isWholeEnabled() {
        return isEnabled() && isPeriodVaild();
    }

    public boolean isEnabled() {
        return CommonStateEnum.VALID.getCode() == isEnable;
    }

    public boolean isPeriodVaild() {
        return DateTimeUtils.withinValidityPeriod(DateUtils.now(), this.beginDate, this.endDate);
    }
}