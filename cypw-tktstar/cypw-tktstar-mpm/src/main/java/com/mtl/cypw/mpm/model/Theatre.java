package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午06:01:06
 */
@Data
@Table(name = "tbltheatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Integer theatreId;

    @Column(name = "theatre_name")
    private String theatreName;

    @Column(name = "theatre_address")
    private String theatreAddress;

    @Column(name = "theatre_content")
    private String theatreContent;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "theatre_image")
    private String theatreImage;

    @Column(name = "prepayment_amount")
    private BigDecimal prepaymentAmount;

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    @Column(name = "longitude_and_latitude")
    private String longitudeAndLatitude;
}