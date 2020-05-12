package com.mtl.cypw.domain.admin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/18.
 */
@Data
public class UserDTO {

    private Integer userId;

    private Integer groupId;

    private String loginName;

    private String loginPass;

    private String personName;

    private String personMobile;

    private String userDesc;

    private Integer roleId;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private String updateName;

    private Integer isEnable;

    private Integer isDelete;

    private Integer isAdministrator;

    private String tokenId;

    private Integer companyId;

    private Integer enterpriseId;

    private Integer isPrint;
}
