package com.mtl.cypw.admin.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月17日 上午11:17:41
*/
@Data
@Table(name = "tblusergroup")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_desc")
    private String groupDesc;

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

    @Column(name = "is_delete")
    private Byte isDelete;

    @Column(name = "is_enable")
    private Byte isEnable;

    @Column(name = "is_store")
    private Byte isStore;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}