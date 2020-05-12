package com.mtl.cypw.admin.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tang.
 * @date 2020年03月17日 上午11:17:41
 */
@Data
@Table(name = "tblpermissionuri")
public class PermissionUri {
    @Column(name = "permission_uri")
    private String permissionUri;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "submenu_id")
    private Integer submenuId;
}