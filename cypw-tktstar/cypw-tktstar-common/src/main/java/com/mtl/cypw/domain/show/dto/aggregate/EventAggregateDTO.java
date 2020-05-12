package com.mtl.cypw.domain.show.dto.aggregate;

import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.dto.SeatDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author sq
 * @date 2020/3/18  14:35
 */
@Data
public class EventAggregateDTO {
    /**
     * 主键id.
     */
    private Integer eventId;
    /**
     * 场次id.
     */
    private Integer programId;

    /**
     * 场次日期.
     */
    private Date eventDate;

    /**
     * 场次简称.
     */
    private String eventTitle;

    /**
     * 开售时间.
     */
    private Date saleDateBegin;

    /**
     * 停售时间.
     */
    private Date saleDateEnd;

    /**
     * 有效性.
     */
    private Integer isEnable;
    /**
     * 价格个数.
     */
    private List<EventPriceDTO> priceList;
    /**
     * 座位.
     */
    private List<SeatDTO> seatList;
    /**
     * 票面.
     */

    /**
     * 外部核销码.
     */
}
