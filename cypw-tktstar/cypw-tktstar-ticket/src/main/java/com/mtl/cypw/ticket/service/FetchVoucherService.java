package com.mtl.cypw.ticket.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.ticket.dto.OrderPaperDTO;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import com.mtl.cypw.ticket.model.FetchVoucher;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 19:30
 */
public interface FetchVoucherService {

    FetchVoucher issue();

    FetchVoucher fetchAck(OrderPaperDTO paper);

    FetchVoucher fetchGoodsAck(OrderPaperDTO paper, FetchCommandParam param);

    FetchVoucher findVoucherByVoucherNo(String voucherNo, Integer enterpriseId);

    List<FetchVoucher> findVoucherByQuery(FetchQueryParam param, Pagination pagination);

    List<Integer> findTicketIdListByVoucherId(Integer voucherId);


}
