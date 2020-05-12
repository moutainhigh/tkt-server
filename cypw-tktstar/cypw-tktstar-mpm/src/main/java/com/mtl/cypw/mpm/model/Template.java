package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午06:01:06
 */
@Data
@Table(name = "tbltemplate")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "venue_id")
    private Integer venueId;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "template_desc")
    private String templateDesc;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "map_type_id")
    private Integer mapTypeId;

    @Column(name = "template_map")
    private String templateMap;

    @Column(name = "one_svg")
    private String oneSvg;
}