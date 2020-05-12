package com.mtl.cypw.domain.show.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@Data
public class ActivityQuery {

    private List<Integer> activityIdList;

    private Integer enterpriseId;

    private Integer isEnable;

    private Integer typeId;

    private Date lessBeginDate;
    private Date greaterBeginDate;

    private Date lessEndDate;
    private Date greaterEndDate;

}
