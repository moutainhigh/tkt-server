package com.mtl.cypw.show.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年01月03日 下午07:24:59
 */
@Data
@Table(name = "tblactivity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Integer activityId;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_brief")
    private String activityBrief;

    @Column(name = "activity_image")
    private String activityImage;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "activity_url")
    private String activityUrl;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "activity_content")
    private String activityContent;
}