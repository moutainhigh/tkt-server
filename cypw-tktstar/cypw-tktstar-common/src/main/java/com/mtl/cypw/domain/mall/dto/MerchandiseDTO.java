package com.mtl.cypw.domain.mall.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-03 11:41
 */
@Setter
@Getter
@ToString(callSuper = true)
public class MerchandiseDTO extends BaseEntityInfo {

    private Integer merchandiseId;

    /**
     * 商品编码
     */
    private String merchandiseCode;

    /**
     * 商品名称
     */
    private String merchandiseName;

    /**
     * 商品简述
     */
    private String merchandiseBrief;

    /**
     * 商品图片（入口图)
     */
    private String merchandiseImage;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 每单限制数量
     */
    private Integer merchandiseLimitCnt;

    /**
     * 是否上架
     */
    private Integer isEnable;

    /**
     * 是否可售卖
     */
    private Boolean saleable;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 商品最低售价
     */
    private BigDecimal bottomPrice;

    /**
     * 购买须知
     */
    private String purchaseNotice;

    /**
     * 图文内容
     */
    private String merchandiseContent;


}
