package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 22:14
 */
@Setter
@Getter
@ToString(callSuper = true)
public class CheckRecordQueryDTO extends BaseEntityInfo {

    /**
     * 检票日期
     */
    private Date checkDate;

    /**
     * 检票总数
     */
    private Integer checkCount;

    /**
     * 检票记录
     */
    private List<TicketPaperDTO> ticketPapers;
}
