package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.mtl.cypw.domain.show.dto.EventDTO;
import com.mtl.cypw.domain.show.dto.aggregate.EventAggregateDTO;
import com.mtl.cypw.domain.show.query.EventQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/22.
 */
public interface EventApi {

    /**
     * 查询演出场次列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/events")
    TMultiResult<EventDTO> searchEventList(@RequestBody QueryRequest<EventQuery> request);


    /**
     * 后台查询演出场次列表.
     * @param request 参数.
     * @return TPageResult.
     */
    @GetMapping("/endpoint/backend/event/events")
    TPageResult<EventAggregateDTO> searchBackendEventList(@RequestBody QueryRequest<EventQuery> request);
}
