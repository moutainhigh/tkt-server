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
@Table(name = "tbleventpricestock")
public class EventPriceStock {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "stock_qty")
    private Integer stockQty;

    @Column(name = "add_qty")
    private Integer addQty;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;
}