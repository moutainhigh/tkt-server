package com.mtl.cypw.member.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019年11月20日 上午11:01:11
*/
@Data
@Table(name = "tblmemberaddress")
public class MemberAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "delivery_name")
    private String deliveryName;

    @Column(name = "delivery_mobile")
    private String deliveryMobile;

    @Column(name = "province_code")
    private Integer provinceCode;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "district_code")
    private Integer districtCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "is_default")
    private Byte isDefault;
}