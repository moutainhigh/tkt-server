package com.mtl.cypw.domain.mpm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 12:16
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BannerDTO {

    private Integer bannerId;

    private String bannerName;

    private String bannerDesc;

    private String bannerImage;

    private Integer bannerType;

    private Integer linkType;

    private Integer resourceId;

    private String bannerUrl;

    private Date beginDate;

    private Date endDate;

    private Byte isEnable;

    private Integer sortOrder;

    private Integer enterpriseId;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;
}
