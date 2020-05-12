package com.mtl.cypw.show.pojo;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_fetch_ticket_way")
public class FetchTicketWay extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "program_id")
    private Integer programId;

    /**
     * 取票方式
     */
    @Column(name = "deliver_type")
    private Integer deliverType;

    /**
     * 是否需要证件取件(0-不需要, 1-需要)
     */
    @Column(name = "need_idcard")
    private Integer needIdcard;

    /**
     * 温馨提示
     */
    @Column(name = "tips")
    private String tips;

    /**
     * 配送费
     */
    @Column(name = "express_fee")
    private Long expressFee;

    /**
     * 取票地址
     */
    @Column(name = "fetch_address")
    private String fetchAddress;

    /**
     * 联系电话
     */
    @Column(name = "contact_mobile")
    private String contactMobile;

    /**
     * 上门取票的服务时间说明
     */
    @Column(name = "fetch_time_desc")
    private String fetchTimeDesc;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}