package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/16.
 */
@Data
public class MachinePosterDTO {

    private Integer posterId;

    private String posterName;

    private String posterImage;

    private Integer sortOrder;

    private Integer isEnable;

    private Integer isDelete;

    private Integer enterpriseId;

    private Date createDate;

    private Integer createUser;

    private Date updateDate;

    private Integer updateUser;
}
