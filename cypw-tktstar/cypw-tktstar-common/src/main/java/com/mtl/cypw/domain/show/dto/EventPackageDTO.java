package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class EventPackageDTO {
    private Integer packageId;

    private Integer eventId;

    private Integer priceId;

    private BigDecimal packageValue;

    private Integer packageQty;

    private String packageName;

    private String packageDesc;

    private Integer isEnable;

    private Date beginDate;

    private Date endDate;

    private Integer addUser;

    private Date addDate;

    private Integer updateUser;

    private Date updateDate;
}
