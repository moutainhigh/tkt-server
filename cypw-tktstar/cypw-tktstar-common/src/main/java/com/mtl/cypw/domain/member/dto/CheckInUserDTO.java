package com.mtl.cypw.domain.member.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@Data
public class CheckInUserDTO {
    private Integer id;

    private Integer enterpriseId;

    private String userName;

    private String userPass;

    private String personName;

    private Date beginDate;

    private Date endDate;

    private Integer isEnable;

    private String remark;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;
}
