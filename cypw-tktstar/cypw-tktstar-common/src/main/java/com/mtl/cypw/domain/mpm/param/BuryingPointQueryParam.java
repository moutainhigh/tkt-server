package com.mtl.cypw.domain.mpm.param;

import com.mtl.cypw.domain.mpm.enums.BuryingPointTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Data
public class BuryingPointQueryParam {

    private Integer memberId;

    private BuryingPointTypeEnum buryingPointType;

    private Integer enterpriseId;

    private Date createTimeBegin;

    private Date createTimeEnd;
}
