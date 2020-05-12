package com.mtl.cypw.payment.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年01月06日 下午06:47:40
*/
@Data
@Table(name = "cy_payment_log")
public class PaymentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "transaction_flow_no")
    private String transactionFlowNo;

    @Column(name = "amount")
    private String amount;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_info")
    private String transactionInfo;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}