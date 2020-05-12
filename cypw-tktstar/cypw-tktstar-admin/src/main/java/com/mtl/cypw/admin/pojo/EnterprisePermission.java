package com.mtl.cypw.admin.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tang.
 * @date 2020年03月19日 下午02:22:56
 */
@Data
@Table(name = "tblenterprisepermission")
public class EnterprisePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "permission_id")
    private Integer permissionId;
}