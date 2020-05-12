package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
@Table(name = "tblvenue")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private Integer venueId;

    @Column(name = "theatre_id")
    private Integer theatreId;

    @Column(name = "venue_name")
    private String venueName;

    @Column(name = "venue_desc")
    private String venueDesc;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "is_enable")
    private Integer isEnable;
}