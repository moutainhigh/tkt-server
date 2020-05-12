package com.mtl.cypw.member.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "tblmachine")
public class Machine {
    @Id
    @Column(name = "machine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machineId;

    /**
     * 密钥
     */
    @Column(name = "machine_key")
    private String machineKey;

    /**
     * MAC地址，第一次登陆时绑定
     */
    @Column(name = "mac_address")
    private String macAddress;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "update_user")
    private Integer updateUser;
}