package com.mtl.cypw.show.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2019年11月20日 下午02:25:48
 */
@Data
@Table(name = "tblprogramdistribution")
public class ProgramDistribution {
    @Id
    @Column(name = "distribution_id")
    private Integer distributionId;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "distributor_id")
    private Integer distributorId;

    @Column(name = "distribution_code")
    private String distributionCode;

    @Column(name = "distribution_rate")
    private Integer distributionRate;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "distribution_desc")
    private String distributionDesc;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;
}