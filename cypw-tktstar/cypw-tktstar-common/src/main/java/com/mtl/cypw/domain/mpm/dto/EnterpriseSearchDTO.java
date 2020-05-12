package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Data
public class EnterpriseSearchDTO {

    private Integer id;

    private Integer enterpriseId;

    private String searchKey;

    private String guessKey;

    private Date createDate;

    private Date updateDate;

    private Integer createUser;

    private Integer updateUser;
}
