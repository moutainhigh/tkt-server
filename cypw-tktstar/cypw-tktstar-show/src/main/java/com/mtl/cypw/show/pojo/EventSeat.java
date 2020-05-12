package com.mtl.cypw.show.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019年11月22日 下午02:20:36
*/
@Data
@Table(name = "tbleventseat")
public class EventSeat {

    @Id
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "bg_color")
    private String bgColor;

    @Column(name = "row_int")
    private Integer rowInt;

    @Column(name = "col_int")
    private Integer colInt;

    @Column(name = "row_name")
    private String rowName;

    @Column(name = "col_name")
    private String colName;

    @Column(name = "only_prefix")
    private Integer onlyPrefix;

    @Column(name = "seat_type")
    private Integer seatType;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "seat_status")
    private Integer seatStatus;

    @Column(name = "lock_id")
    private Integer lockId;

    @Column(name = "lock_date")
    private Date lockDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "pool_id")
    private Integer poolId;

    @Column(name = "ticket_sn")
    private String ticketSn;

    @Column(name = "reserve_id")
    private Integer reserveId;

    @Column(name = "reserve_date")
    private Date reserveDate;


}