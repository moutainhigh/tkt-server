package com.mtl.cypw.coupon.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年12月04日 上午11:15:46
*/
@Data
@Table(name = "tblcouponlog")
public class Couponlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "coupon_prefix")
    private String couponPrefix;

    @Column(name = "coupon_digit")
    private String couponDigit;

    @Column(name = "total_qty")
    private Integer totalQty;

    @Column(name = "total_used")
    private Integer totalUsed;

    @Column(name = "log_desc")
    private String logDesc;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;
}