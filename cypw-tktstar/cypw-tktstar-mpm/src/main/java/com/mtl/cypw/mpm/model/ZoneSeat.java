package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
@Table(name = "tblzoneseat")
public class ZoneSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_seat_id")
    private Integer zoneSeatId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "row_name")
    private String rowName;

    @Column(name = "col_name")
    private String colName;

    @Column(name = "row_int")
    private Integer rowInt;

    @Column(name = "col_int")
    private Integer colInt;

    @Column(name = "only_prefix")
    private Integer onlyPrefix;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;
}