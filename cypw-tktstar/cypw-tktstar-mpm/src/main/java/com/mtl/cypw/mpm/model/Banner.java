package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月16日 下午03:51:06
 */
@Data
@Table(name = "tblbanner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Integer bannerId;

    @Column(name = "banner_name")
    private String bannerName;

    @Column(name = "banner_desc")
    private String bannerDesc;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "banner_type")
    private Integer bannerType;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_enable")
    private Byte isEnable;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "link_type")
    private Integer linkType;
}