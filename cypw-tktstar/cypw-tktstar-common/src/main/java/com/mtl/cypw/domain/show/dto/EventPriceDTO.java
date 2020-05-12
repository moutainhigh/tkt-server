package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class EventPriceDTO {

    private Integer programId;

    private Integer priceId;

    private Integer eventId;

    private String priceOrigin;

    private BigDecimal priceValue;

    private String priceClass;

    private Integer totalQty;

    private String priceTitle;

    private Integer stockQty;

    private Integer soldQty;

    private Integer isEnable;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private String priceColor;

    private Integer minQty;
}
