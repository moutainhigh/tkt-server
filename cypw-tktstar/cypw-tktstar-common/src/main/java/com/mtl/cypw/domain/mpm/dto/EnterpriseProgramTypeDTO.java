package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/12/31.
 */
@Data
public class EnterpriseProgramTypeDTO {

    private Integer id;

    private Integer enterpriseId;

    private Integer programTypeId;

    private String programTypeTitle;

    private Integer sortOrder;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private Integer deleted;
}
