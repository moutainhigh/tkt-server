package com.mtl.cypw.api.ticket.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.ticket.dto.FetchPaperResultDTO;
import com.mtl.cypw.domain.ticket.dto.VoucherPaperDTO;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 17:30
 */
public interface FetchApi {

    /**
     * Fetch 的查询 RPC 服务本地 Bean 名称
     */
    String FETCH_API = "FetchEndpoint";

    @PostMapping("/endpoint/v1/fetch/find_order_by_code")
    TSingleResult<FetchPaperResultDTO> findOrderByCode(@RequestBody QueryRequest<FetchQueryParam> request);

    @PostMapping("/endpoint/v1/fetch/find_order_by_id_card")
    TSingleResult<FetchPaperResultDTO> findOrderByIdCard(@RequestBody QueryRequest<FetchQueryParam> request);

    @PostMapping("/endpoint/v1/fetch/issue")
    TSingleResult<FetchPaperResultDTO> issue(@RequestBody GenericRequest<FetchCommandParam> request);

    @PostMapping("/endpoint/v1/fetch/ack")
    TSingleResult<FetchPaperResultDTO> ack(@RequestBody GenericRequest<FetchCommandParam> request);

    @PostMapping("/endpoint/v1/fetch/ack_by_goods")
    TSingleResult<FetchPaperResultDTO> ackByGoods(@RequestBody GenericRequest<FetchCommandParam> request);

    @PostMapping("/endpoint/v1/fetch/find_record_by_query")
    TPageResult<VoucherPaperDTO> recordQuery(@RequestBody QueryRequest<FetchQueryParam> request);

    @PostMapping("/endpoint/v1/fetch/find_count_by_query")
    TSingleResult<Integer> countQuery(@RequestBody QueryRequest<FetchQueryParam> request);


}
