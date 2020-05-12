package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.query.EventQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/22.
 */
public interface EventPriceApi {

    /**
     * 查询演出场次价格列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/prices")
    TPageResult<EventPriceDTO> searchEventPriceList(@RequestBody QueryRequest<EventQuery> request);

    /**
     * 根据场次id查询场次最低票价
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/event_min_price")
    TSingleResult<EventPriceDTO> searchMinEventPriceByEventId(@RequestBody QueryRequest<EventQuery> request);

    /**
     * 根据演出id查询场次最低票价
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/program_min_price")
    TSingleResult<EventPriceDTO> searchMinEventPriceByProgramId(@RequestBody QueryRequest<EventQuery> request);

    /**
     * 分组查询多个演出的最低票价
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/min_price/program_ids")
    TMultiResult<EventPriceDTO> searchMinEventPriceByProgramIds(@RequestBody QueryRequest<EventQuery> request);
}
