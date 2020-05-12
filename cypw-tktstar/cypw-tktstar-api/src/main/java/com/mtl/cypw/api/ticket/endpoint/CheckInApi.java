package com.mtl.cypw.api.ticket.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.ticket.dto.TicketPaperDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.domain.ticket.param.CheckInTicketParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 21:27
 */
public interface CheckInApi {

    /**
     * Check 的查询 RPC 服务本地 Bean 名称
     */
    String CHECK_API = "CheckInEndpoint";

    @PostMapping("/endpoint/v1/check/find_ticket_by_code")
    TSingleResult<TicketPaperResultDTO> findTicketByCode(@RequestBody GenericRequest<CheckInQueryParam> request);

    @PostMapping("/endpoint/v1/check/find_ticket_by_mobile")
    TSingleResult<TicketPaperResultDTO> findTicketByMobile(@RequestBody GenericRequest<CheckInQueryParam> request);

    @PostMapping("/endpoint/v1/check/consume")
    TSingleResult<TicketPaperResultDTO> consume(@RequestBody GenericRequest<CheckInTicketParam> request);

    @PostMapping("/endpoint/v1/check/find_record_by_query")
    TPageResult<TicketPaperDTO> recordQuery(@RequestBody QueryRequest<CheckInQueryParam> request);

    @PostMapping("/endpoint/v1/check/find_count_by_query")
    TSingleResult<Integer> countQuery(@RequestBody QueryRequest<CheckInQueryParam> request);


}
