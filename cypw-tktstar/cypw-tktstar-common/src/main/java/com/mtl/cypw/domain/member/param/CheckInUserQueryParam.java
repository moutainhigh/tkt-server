package com.mtl.cypw.domain.member.param;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@Data
public class CheckInUserQueryParam {

    private Integer enterpriseId;

    private String userName;

    private String userPass;

    private Integer isEnable;

    private Date greaterBeginDate;
    private Date lessBeginDate;

    private Date greaterEndDate;
    private Date lessEndDate;
}
