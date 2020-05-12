package com.mtl.cypw.provider.ticket.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.ticket.dto.FetchPaperResultDTO;
import com.mtl.cypw.domain.ticket.dto.FetchRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-19 11:12
 */
public interface FetchService {

    /**
     * 根据取票码查询用户订单
     * @param param
     * @return
     */
    FetchPaperResultDTO findOrderByFetchCode(FetchQueryParam param);

    /**
     * 根据身份证查询用户订单
     * @param param
     * @return
     */
    TicketPaperResultDTO findOrderByIDCard(FetchQueryParam param);


    /**
     * 根据取货码提货确认【商家小程序】
     * @param param
     * @return
     */
    FetchPaperResultDTO ackByGoods(FetchCommandParam param);

    /**
     * 获取取票（货）凭证
     * @param param
     * @param pagination
     * @return
     */
    FetchRecordQueryDTO findFetchRecordByQuery(FetchQueryParam param, Pagination pagination);

}
