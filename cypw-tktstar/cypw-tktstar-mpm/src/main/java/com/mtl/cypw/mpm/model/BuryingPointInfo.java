package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年01月19日 下午06:16:13
 */
@Data
@Table(name = "cy_burying_point_info")
public class BuryingPointInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "burying_point_type")
    private Integer buryingPointType;

    @Column(name = "burying_point_content")
    private String buryingPointContent;

    @Column(name = "source_platform")
    private String sourcePlatform;

    @Column(name = "source_page")
    private String sourcePage;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}