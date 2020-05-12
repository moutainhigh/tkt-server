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
 * @date 2019-01-19 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_reserve_seat_detail")
public class ReserveSeatDetail extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 预留单ID
     */
    @Column(name = "reserve_id")
    private Integer reserveId;

    /**
     * 座位预留状态（1-预留中,2-已出票,3-已释放）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 预留场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 预留区域ID
     */
    @Column(name = "zone_id")
    private Integer zoneId;

    /**
     * 预留区域名称
     */
    @Column(name = "zone_name")
    private String zoneName;

    /**
     * 预留票价ID
     */
    @Column(name = "price_id")
    private Integer priceId;

    /**
     * 预留票价(分)
     */
    @Column(name = "price_value")
    private Long priceValue;

    /**
     * 预留座位ID
     */
    @Column(name = "seat_id")
    private Integer seatId;

    /**
     * 预留座位名称
     */
    @Column(name = "seat_name")
    private String seatName;

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