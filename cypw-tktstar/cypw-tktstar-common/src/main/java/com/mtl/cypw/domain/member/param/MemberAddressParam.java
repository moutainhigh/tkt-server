package com.mtl.cypw.domain.member.param;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Data
public class MemberAddressParam {

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

    private Byte isDefault;

}
