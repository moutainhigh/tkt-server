package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Data
public class TheatreDTO {

    private Integer theatreId;

    private String theatreName;

    private String theatreAddress;

    private String theatreContent;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private Integer cityCode;

    private Integer isEnable;

    private String theatreImage;

    private BigDecimal prepaymentAmount;

    private BigDecimal creditAmount;

    private String longitudeAndLatitude;
}
