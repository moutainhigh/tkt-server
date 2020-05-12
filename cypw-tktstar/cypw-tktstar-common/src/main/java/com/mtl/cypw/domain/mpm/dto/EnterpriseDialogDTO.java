package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/02/18.
 */
@Data
public class EnterpriseDialogDTO {
    private Integer enterpriseId;

    private Byte isShow;

    private String message1;

    private String message2;

    private Integer updateUser;

    private Date updateDate;
}
