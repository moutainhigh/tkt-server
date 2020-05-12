package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class EventPriceStockDTO {

    private Integer id;

    private Integer priceId;

    private Integer stockQty;

    private Integer addQty;

    private Date addDate;

    private Integer addUser;
}
