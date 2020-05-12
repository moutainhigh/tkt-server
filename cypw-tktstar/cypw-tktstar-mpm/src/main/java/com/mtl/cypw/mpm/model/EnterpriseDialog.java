package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年02月18日 下午04:29:29
 */
@Data
@Table(name = "tblenterprisedialog")
public class EnterpriseDialog {

    @Id
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "is_show")
    private Byte isShow;

    @Column(name = "message1")
    private String message1;

    @Column(name = "message2")
    private String message2;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private Date updateDate;
}