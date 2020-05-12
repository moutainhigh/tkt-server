package com.mtl.cypw.show.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
* Created by Mybatis Generator on 2019年11月22日 下午02:20:36
*/
@Data
@Table(name = "tbleventprint")
public class EventPrint {
    @Id
    @Column(name = "print_id")
    private Integer printId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "canvas_width")
    private Integer canvasWidth;

    @Column(name = "canvas_height")
    private Integer canvasHeight;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "svg_data")
    private String svgData;
}