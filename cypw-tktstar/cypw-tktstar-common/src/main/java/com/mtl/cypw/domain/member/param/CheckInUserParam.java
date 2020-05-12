package com.mtl.cypw.domain.member.param;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@Data
public class CheckInUserParam {

    private Integer id;

    private Integer enterpriseId;

    private String userName;

    private String userPass;

    private String personName;

    private Date beginDate;

    private Date endDate;

    private Integer isEnable;

    private String remark;

    private Integer addUser;

    private Integer updateUser;
}
