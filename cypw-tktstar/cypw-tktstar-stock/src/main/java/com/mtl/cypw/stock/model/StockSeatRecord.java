package com.mtl.cypw.stock.model;

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
@Table(name = "cy_stock_seat_record")
public class StockSeatRecord extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 座位集合
     */
    @Column(name = "seat_info")
    private String seatInfo;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 会员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

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