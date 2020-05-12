package com.mtl.cypw.domain.member.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@Data
public class MachineDTO {

    private Integer machineId;

    /**
     * 密钥
     */
    private String machineKey;

    /**
     * MAC地址，第一次登陆时绑定
     */
    private String macAddress;

    /**
     * 备注
     */
    private String remark;

    private Integer isEnable;

    private Integer isDelete;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    private Date createDate;

    private Date updateDate;

    private Integer createUser;

    private Integer updateUser;
}
