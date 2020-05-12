package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Data
public class TemplateDTO {

    private Integer templateId;

    private Integer venueId;

    private String templateName;

    private String templateDesc;

    private Integer addUser;

    private Date addDate;

    private Integer updateUser;

    private Date updateDate;

    private Integer isEnable;

    private Integer isDelete;

    private Integer mapTypeId;

    private String templateMap;

    private String oneSvg;
}
