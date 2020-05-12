package com.mtl.cypw.mpm.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年11月26日 上午11:12:46
*/
@Data
@Table(name = "tblenterprisepaytype")
public class EnterprisePayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_pay_type_id")
    private Integer enterprisePayTypeId;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "pay_type_id")
    private Integer payTypeId;

    @Column(name = "pay_type_name")
    private String payTypeName;

    @Column(name = "config_json")
    private String configJson;

    @Column(name = "remark")
    private String remark;

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

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "transaction_fee_rate")
    private BigDecimal transactionFeeRate;
}