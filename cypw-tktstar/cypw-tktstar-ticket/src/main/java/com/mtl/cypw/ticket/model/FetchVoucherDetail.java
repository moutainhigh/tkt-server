package com.mtl.cypw.ticket.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Johnathon.Yuan
 * @date 2019-03-18 16:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_fetch_voucher_detail")
public class FetchVoucherDetail extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 打印凭证ID
     */
    @Column(name = "voucher_id")
    private Integer voucherId;

    /**
     * 凭证关联票（货）品ID
     */
    @Column(name = "ticket_id")
    private Integer ticketId;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }
}