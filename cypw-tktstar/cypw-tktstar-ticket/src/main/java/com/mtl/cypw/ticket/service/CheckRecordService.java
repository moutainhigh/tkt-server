package com.mtl.cypw.ticket.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.ticket.model.CheckRecord;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-13 13:31
 */
public interface CheckRecordService {

    /**
     * 保存检票记录
     * @param record
     * @return
     */
    CheckRecord save(CheckRecord record);

    /**
     * 查询检票记录
     * @param param
     * @return
     */
    List<CheckRecord> findCheckRecordByQuery(CheckInQueryParam param, Pagination pagination);

    /**
     * 查询电子检票次数
     * @param ticketId
     * @return
     */
    int findCheckCountByTicketId(Integer ticketId);

    /**
     * 查询入口检票次数
     * @param ticketId
     * @return
     */
    int findCheckCountByTicketIdAndCheckEntryId(Integer ticketId, Integer checkEntryId);

    /**
     * 查询最近的检票记录
     * @param ticketId
     * @return
     */
    CheckRecord findLastCheckRecordByTicketId(Integer ticketId);

}
