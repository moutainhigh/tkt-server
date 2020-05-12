package com.mtl.cypw.mpm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "tblmachineposter")
public class MachinePoster {
    @Id
    @Column(name = "poster_id")
    private Integer posterId;

    @Column(name = "poster_name")
    private String posterName;

    @Column(name = "poster_image")
    private String posterImage;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private Integer updateUser;
}