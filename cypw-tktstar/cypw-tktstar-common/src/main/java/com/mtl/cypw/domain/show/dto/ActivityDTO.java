package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@Data
public class ActivityDTO {
    private Integer activityId;

    private String activityName;

    private String activityBrief;

    private String activityImage;

    private Integer sortOrder;

    private Date beginDate;

    private Date endDate;

    private Integer typeId;

    private String activityUrl;

    private Integer isEnable;

    private Integer enterpriseId;

    private Date addDate;

    private Integer addUser;

    private Date updateDate;

    private Integer updateUser;

    private Integer isDelete;

    private String activityContent;
}
