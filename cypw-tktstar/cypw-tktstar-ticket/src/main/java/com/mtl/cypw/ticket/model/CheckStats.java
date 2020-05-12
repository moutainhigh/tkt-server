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
 * @date 2019-02-13 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_check_stats")
public class CheckStats extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 电子票ID
     */
    @Column(name = "ticket_id")
    private Integer ticketId;

    /**
     * 检票次数
     */
    @Column(name = "check_count")
    private Integer checkCount;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }

    public void increase (){
        this.setCheckCount(this.checkCount + 1);
    }
}