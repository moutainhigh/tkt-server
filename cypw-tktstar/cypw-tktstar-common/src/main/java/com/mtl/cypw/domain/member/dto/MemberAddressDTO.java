package com.mtl.cypw.domain.member.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class MemberAddressDTO {
    private Integer addressId;

    private Integer memberId;

    private String deliveryName;

    private String deliveryMobile;

    private Integer provinceCode;
    private String provinceName;

    private Integer cityCode;
    private String cityName;

    private Integer districtCode;
    private String districtName;

    private String deliveryAddress;

    private Date addDate;

    private Date updateDate;

    private Integer enterpriseId;

    private Byte isDefault;
}
