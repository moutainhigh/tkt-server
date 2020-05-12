package com.mtl.cypw.domain.stock.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 17:33
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ReserveSeatDetailDTO extends BaseEntityInfo {

    /**
     * 预留座位记录ID
     */
    private Integer seatDetailId;

    /**
     * 预留单ID
     */
    private Integer reserveId;

    /**
     * 座位预留状态（1-预留中,2-已出票,3-已释放）
     */
    private Integer status;

    /**
     * 预留场次ID
     */
    private Integer eventId;

    /**
     * 预留区域ID
     */
    private Integer zoneId;

    /**
     * 预留区域名称
     */
    private String zoneName;

    /**
     * 预留票价ID
     */
    private Integer priceId;

    /**
     * 预留票价
     */
    private Money priceValue;

    /**
     * 预留座位ID
     */
    private Integer seatId;

    /**
     * 预留座位名称
     */
    private String seatName;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
