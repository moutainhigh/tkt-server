package com.mtl.cypw.coupon.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年12月04日 上午11:15:46
*/
@Data
@Table(name = "tblpromotionprogram")
public class PromotionProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_program_id")
    private Integer promotionProgramId;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "is_all")
    private Integer isAll;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}