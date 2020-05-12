package com.mtl.cypw.provider.ticket.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.ticket.dto.CheckRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.domain.ticket.param.CheckInTicketParam;

/**
 * @author zhengyou.yuan
 * @date 2020-02-13 13:31
 */
public interface CheckService {

    /**
     * 根据检票码查询电子票
     * @param param
     * @return
     */
    TicketPaperResultDTO findTicketByCheckCode(CheckInQueryParam param);

    /**
     * 根据订单手机查询电子票
     * @param param
     * @return
     */
    TicketPaperResultDTO findTicketByMobileNo(CheckInQueryParam param);

    /**
     * 核销电子票
     * @param param
     * @return
     */
    TicketPaperResultDTO consume(CheckInTicketParam param);

    /**
     * 查询检票记录
     * @param param
     * @param pagination
     * @return
     */
    CheckRecordQueryDTO findCheckRecordByQuery(CheckInQueryParam param, Pagination pagination);
}
