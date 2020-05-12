package com.mtl.cypw.domain.show.dto.aggregate;

import lombok.Data;

import java.util.Date;

/**
 * @author sq
 * @date 2020/3/16  17:17
 */
@Data
public class ProgramAggregateDTO {
    /**
     * 主键id
     */
    private Integer programId;

    /**
     * 演出编号.
     */
    private String programNo;
    /**
     * 演出简称.
     */
    private String programTitleBrief;
    /**
     * 演出地点(tblvenue.venue_name+tblTheatre.theatre_name).
     */
    private String theatreAndVenueName;
    /**
     * 价格描述.
     */
    private String programPrice;
    /**
     * 开售时间.
     */
    private Date saleDateBegin;
    /**
     * 停售时间.
     */
    private Date saleDateEnd;
    /**
     * 售票状态(tbl_parameter.parameter_name).
     */
    private String saleStatusName;
    /**
     * 场次(count(tblEvent)).
     */
    private Integer eventCnt;
    /**
     * 套票(count(tblProgramTaopiao)).
     */
    private Integer comboTicketCnt;


}
