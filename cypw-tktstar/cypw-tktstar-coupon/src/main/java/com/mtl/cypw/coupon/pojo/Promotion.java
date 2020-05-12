package com.mtl.cypw.coupon.pojo;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.util.DateTimeUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang. 
 * @date 2019年12月04日 上午11:15:46
*/
@Data
@Table(name = "tblpromotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "business_type_id")
    private Integer businessTypeId;

    @Column(name = "promotion_type_id")
    private Integer promotionTypeId;

    @Column(name = "promotion_name")
    private String promotionName;

    @Column(name = "program_point_id")
    private Integer programPointId;

    @Column(name = "program_type_ids")
    private String programTypeIds;

    @Column(name = "order_amount_restriction")
    private BigDecimal orderAmountRestriction;

    @Column(name = "min_qty_restriction")
    private Integer minQtyRestriction;

    @Column(name = "max_qty_restriction")
    private Integer maxQtyRestriction;

    @Column(name = "max_qty_restriction_per_event")
    private Integer maxQtyRestrictionPerEvent;

    @Column(name = "member_restriction")
    private String memberRestriction;

    @Column(name = "payment_restriction")
    private String paymentRestriction;

    @Column(name = "app_show")
    private Integer appShow;

    @Column(name = "wechat_show")
    private Integer wechatShow;

    @Column(name = "pc_show")
    private Integer pcShow;

    @Column(name = "promotion_begin_date")
    private Date promotionBeginDate;

    @Column(name = "promotion_end_date")
    private Date promotionEndDate;

    @Column(name = "is_display")
    private Integer isDisplay;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "list_image")
    private String listImage;

    @Column(name = "detail_image")
    private String detailImage;

    @Column(name = "promotion_brief")
    private String promotionBrief;

    @Column(name = "promotion_content")
    private String promotionContent;

    @Column(name = "promotion_remark")
    private String promotionRemark;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "key_type")
    private String keyType;

    @Column(name = "promotion_discount")
    private BigDecimal promotionDiscount;

    @Column(name = "promotion_amount")
    private BigDecimal promotionAmount;

    @Column(name = "promotion_qty_rate")
    private String promotionQtyRate;

    @Column(name = "promotion_gift_price_id")
    private Integer promotionGiftPriceId;

    @Column(name = "show_time_image")
    private Integer showTimeImage;

    @Column(name = "show_other_images")
    private String showOtherImages;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "limit_date_string")
    private String limitDateString;

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "allow_exchange_qty")
    private Integer allowExchangeQty;

    @Column(name = "have_exchange_qty")
    private Integer haveExchangeQty;

    @Column(name = "distribution_type")
    private Integer distributionType;

    @Column(name = "available_day")
    private Integer availableDay;

    @Column(name = "available_begin_date")
    private Date availableBeginDate;

    @Column(name = "available_end_date")
    private Date availableEndDate;

    public boolean isWholeEnabled() {
        return isEnabled() && isPeriodVaild();
    }

    public boolean isEnabled() {
        return CommonStateEnum.VALID.getCode() == isEnable;
    }

    public boolean isDisplayed() {
        return CommonStateEnum.VALID.getCode() == isDisplay;
    }

    public boolean isPeriodVaild() {
        return DateTimeUtils.withinValidityPeriod(DateUtils.now(), this.availableBeginDate, this.availableEndDate);
    }

}