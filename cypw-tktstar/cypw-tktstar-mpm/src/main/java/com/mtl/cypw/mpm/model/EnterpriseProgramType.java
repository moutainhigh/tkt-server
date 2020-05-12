package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月31日 下午04:01:43
 */
@Data
@Table(name = "tblenterpriseprogramtype")
public class EnterpriseProgramType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "program_type_id")
    private Integer programTypeId;

    @Column(name = "program_type_title")
    private String programTypeTitle;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "deleted")
    private Integer deleted;
}