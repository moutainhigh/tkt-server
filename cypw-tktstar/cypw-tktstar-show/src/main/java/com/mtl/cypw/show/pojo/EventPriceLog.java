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
@Table(name = "tbleventpricelog")
public class EventPriceLog {
    @Id
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "log_desc")
    private String logDesc;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;
}