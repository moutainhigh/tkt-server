package com.mtl.cypw.show.pojo.aggregate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author sq
 * @date 2020/3/17  10:18
 */
@Data
public class ProgramAggregate {
    @Id
    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "program_no")
    private String programNo;

    @Column(name = "program_title_brief")
    private String programTitleBrief;

    @Column(name = "venue_name")
    private String venueName;

    @Column(name = "theatre_name")
    private String theatreName;

    @Column(name = "program_price")
    private String programPrice;

    @Column(name = "sale_date_begin")
    private Date saleDateBegin;

    @Column(name = "sale_date_end")
    private Date saleDateEnd;

    @Column(name = "sale_status_name")
    private String saleStatusName;

    @Column(name = "event_cnt")
    private Integer eventCnt;

    @Column(name = "combo_ticket_cnt")
    private Integer comboTicketCnt;

    @Column(name = "wechat_show")
    private Integer wechatShow;
}
