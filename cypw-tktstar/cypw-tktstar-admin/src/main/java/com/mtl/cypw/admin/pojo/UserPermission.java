package com.mtl.cypw.admin.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月17日 上午11:17:41
*/
@Data
@Table(name = "tbluserpermission")
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "up_id")
    private Integer upId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "permission_id")
    private String permissionId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}