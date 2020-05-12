package com.mtl.cypw.domain.coupon.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Data
public class PromotionDTO {

    private Integer promotionId;

    private Integer businessTypeId;

    private Integer promotionTypeId;

    private String promotionName;

    private Integer programPointId;

    private String programTypeIds;

    private BigDecimal orderAmountRestriction;

    private Integer minQtyRestriction;

    private Integer maxQtyRestriction;

    private Integer maxQtyRestrictionPerEvent;

    private String memberRestriction;

    private String paymentRestriction;

    private Integer appShow;

    private Integer wechatShow;

    private Integer pcShow;

    private Date promotionBeginDate;

    private Date promotionEndDate;

    private Integer isDisplay;

    private Integer isEnable;

    private Integer isDelete;

    private String listImage;

    private String detailImage;

    private String promotionBrief;

    private String promotionContent;

    private String promotionRemark;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private String qrCode;

    private String keyType;

    private BigDecimal promotionDiscount;

    private BigDecimal promotionAmount;

    private String promotionQtyRate;

    private Integer promotionGiftPriceId;

    private Integer showTimeImage;

    private String showOtherImages;

    private Integer enterpriseId;

    private String limitDateString;

    private String promotionCode;

    private Integer allowExchangeQty;

    private Integer haveExchangeQty;

    private Integer distributionType;

    private Integer availableDay;

    private Date availableBeginDate;

    private Date availableEndDate;
}
