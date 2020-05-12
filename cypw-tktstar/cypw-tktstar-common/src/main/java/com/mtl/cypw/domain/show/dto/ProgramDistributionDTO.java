package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@Data
public class ProgramDistributionDTO {
    private Integer distributionId;

    private Integer programId;

    private Integer distributorId;

    private String distributionCode;

    private Integer distributionRate;

    private Date beginDate;

    private Date endDate;

    private Integer isEnable;

    private String distributionDesc;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;
}
