package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
@Table(name = "tblzone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "svg_id")
    private String svgId;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "zone_entrance")
    private String zoneEntrance;

    @Column(name = "zone_remark")
    private String zoneRemark;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "begin_x")
    private Integer beginX;

    @Column(name = "begin_y")
    private Integer beginY;

    @Column(name = "seat_height")
    private Integer seatHeight;

    @Column(name = "seat_width")
    private Integer seatWidth;

    @Column(name = "seat_margin")
    private Integer seatMargin;
}