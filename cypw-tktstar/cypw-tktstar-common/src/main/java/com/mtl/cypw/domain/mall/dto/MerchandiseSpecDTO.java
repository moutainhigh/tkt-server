package com.mtl.cypw.domain.mall.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-03 11:41
 */
@Setter
@Getter
@ToString(callSuper = true)
public class MerchandiseSpecDTO extends BaseEntityInfo {

    private Integer priceId;

    /**
     * 商品ID
     */
    private Integer merchandiseId;

    /**
     * 规格名称
     */
    private String priceTitle;

    /**
     * 图片链接
     */
    private String imageSrc;

    /**
     * 市场价
     */
    private BigDecimal priceOrigin;

    /**
     * 售价
     */
    private BigDecimal priceValue;

    /**
     * 价格级别
     */
    private String priceClass;

    /**
     * 总库存
     */
    private Integer totalQty;

    /**
     * 可售库存
     */
    private Integer stockQty;

    /**
     * 已售库存
     */
    private Integer soldQty;

    /**
     * 是否上架
     */
    private Integer isEnable;

    /**
     * 最少购买数
     */
    private Integer minQty;


}
