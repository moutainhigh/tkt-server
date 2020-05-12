package com.mtl.cypw.admin.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月17日 上午11:17:41
*/
@Data
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "login_pass")
    private String loginPass;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_mobile")
    private String personMobile;

    @Column(name = "user_desc")
    private String userDesc;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_name")
    private String updateName;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "is_administrator")
    private Integer isAdministrator;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "is_print")
    private Integer isPrint;
}