package com.mtl.cypw.admin.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月17日 上午11:17:41
*/
@Data
@Table(name = "tbluserrole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_name")
    private String updateName;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}