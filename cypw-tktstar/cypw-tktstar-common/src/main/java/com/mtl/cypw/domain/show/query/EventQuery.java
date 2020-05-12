package com.mtl.cypw.domain.show.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class EventQuery {

    private Integer eventId;

    private Integer programId;

    private List<Integer> programIds;

    private Integer enterpriseId;

    /**
     * 默认查询有效
     */
    private Integer isEnable;

    private Integer productType;

    /**
     * 开售时间小于传入值
     */
    private Date lessSaleDateBegin;
    /**
     * 开售时间大于传入值
     */
    private Date greaterSaleDateBegin;
    /**
     * 停售时间小于传入值
     */
    private Date lessSaleDateEnd;
    /**
     * 停售时间大于传入值
     */
    private Date greaterSaleDateEnd;

    /**
     * 场次小于传入值
     */
    private Date lessEventDate;
    /**
     * 场次时间大于传入值
     */
    private Date greaterEventDate;
}
