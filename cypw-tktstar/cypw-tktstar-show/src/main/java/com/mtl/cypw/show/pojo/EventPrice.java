package com.mtl.cypw.show.pojo;

import com.mtl.cypw.common.enums.CommonStateEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019年11月22日 下午02:20:36
*/
@Data
@Table(name = "tbleventprice")
public class EventPrice {
    @Id
    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "price_origin")
    private String priceOrigin;

    @Column(name = "price_value")
    private BigDecimal priceValue;

    @Column(name = "price_class")
    private String priceClass;

    @Column(name = "total_qty")
    private Integer totalQty;

    @Column(name = "price_title")
    private String priceTitle;

    @Column(name = "stock_qty")
    private Integer stockQty;

    @Column(name = "sold_qty")
    private Integer soldQty;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "price_color")
    private String priceColor;

    @Column(name = "min_qty")
    private Integer minQty;

    @Column(name = "product_type")
    private Integer productType;

    public boolean isEnabled() {
        return CommonStateEnum.VALID.getCode() == isEnable;
    }

}