package com.mtl.cypw.domain.mpm.dto;

import com.mtl.cypw.domain.mpm.enums.BuryingPointTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Data
public class BuryingPointDTO {

    private Integer id;

    private Integer memberId;

    private BuryingPointTypeEnum buryingPointType;

    private String buryingPointContent;

    private String sourcePlatform;

    private String sourcePage;

    private Integer enterpriseId;

    private Date createTime;
}
