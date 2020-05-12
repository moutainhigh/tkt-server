package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-09 15:21
 */
@Data
public class SeatDTO {

    /**
     * 座位ID
     */
    private Integer seatId;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 模板区域ID
     */
    private Integer zoneId;

    /**
     * 模板区域名称
     */
    private String zoneName;

    /**
     * 票价ID
     */
    private Integer priceId;

    /**
     * 背景色（#ccc）
     */
    private String bgColor;

    /**
     * 物理行
     */
    private Integer rowInt;

    /**
     * 物理列
     */
    private Integer colInt;

    /**
     * 逻辑行
     */
    private String rowName;

    /**
     * 逻辑列
     */
    private String colName;

    /**
     * 标识前缀
     */
    private Integer onlyPrefix;

    /**
     * 座位类型
     * @see com.mtl.cypw.domain.show.enums.SeatTypeEnum
     */
    private Integer seatType;

    /**
     * 座位状态
     * @see com.mtl.cypw.domain.show.enums.SeatStatusEnum
     */
    private Integer seatStatus;

    /**
     * 锁座ID
     */
    private Integer lockId;

    /**
     * 锁座时间
     */
    private Date lockDate;

    /**
     * 所属票池
     */
    private Integer poolId;

    /**
     * 座位 SN 码
     */
    private String ticketSn;

    /**
     * 预留记录ID
     */
    private Integer reserveId;

    /**
     * 预留时间
     */
    private Date reserveDate;

}
