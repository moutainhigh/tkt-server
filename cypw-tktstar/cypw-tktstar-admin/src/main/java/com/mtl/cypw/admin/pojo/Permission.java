package com.mtl.cypw.admin.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tang.
 * @date 2020年03月17日 上午11:17:41
 */
@Data
@Table(name = "tblpermission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "module_id")
    private Integer moduleId;

    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "enterprise_ids")
    private String enterpriseIds;
}