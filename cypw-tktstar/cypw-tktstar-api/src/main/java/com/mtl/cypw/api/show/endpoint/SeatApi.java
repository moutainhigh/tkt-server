package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.show.dto.SeatDTO;
import com.mtl.cypw.domain.show.dto.SeatMapDTO;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.domain.show.query.SeatMapQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-08 18:03
 */
public interface SeatApi {

    /**
     * 查询场次座位列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/seats")
    TPageResult<SeatDTO> searchSeatList(@RequestBody QueryRequest<SeatQuerySpec> request);

    /**
     * 查询场次座位信息
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/event/seatMap")
    TSingleResult<SeatMapDTO> findSeatMap(@RequestBody QueryRequest<SeatMapQuery> request);
}
