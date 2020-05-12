package com.mtl.cypw.mpm.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

/**
 * @author tang.
 * @date 2019年11月26日 上午11:12:46
 */
@Data
@Table(name = "tblenterprise")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "enterprise_abbr")
    private String enterpriseAbbr;

    @Column(name = "fhl_id")
    private String fhlId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "enterprise_desc")
    private String enterpriseDesc;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "mobile_message")
    private String mobileMessage;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "service_mobile")
    private String serviceMobile;

    @Column(name = "home_title")
    private String homeTitle;

    @Column(name = "logo_image")
    private String logoImage;

    @Column(name = "backend_type")
    private Integer backendType;

    @Column(name = "support_normal_login")
    private Integer supportNormalLogin;

    @Column(name = "extended_login")
    private String extendedLogin;

    @Column(name = "member_tags")
    private String memberTags;

    @Column(name = "admin_mobile")
    private String adminMobile;

    @Column(name = "channel")
    private String channel;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "about_us")
    private String aboutUs;
}