package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Data
public class EnterprisePayTypeDTO {
    private Integer enterprisePayTypeId;

    private Integer enterpriseId;

    private Integer payTypeId;

    private String payTypeName;

    private String configJson;

    private String remark;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private Integer isEnable;

    private BigDecimal transactionFeeRate;
}
