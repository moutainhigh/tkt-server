package com.mtl.cypw.show.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
* Created by Mybatis Generator on 2019年11月22日 下午02:20:36
*/
@Data
@Table(name = "tbleventpackage")
public class EventPackage {
    @Id
    @Column(name = "package_id")
    private Integer packageId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "package_value")
    private BigDecimal packageValue;

    @Column(name = "package_qty")
    private Integer packageQty;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "package_desc")
    private String packageDesc;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_date")
    private Date updateDate;
}