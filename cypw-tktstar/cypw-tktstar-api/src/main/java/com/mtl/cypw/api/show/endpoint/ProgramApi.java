package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.show.dto.ProgramDTO;
import com.mtl.cypw.domain.show.dto.aggregate.ProgramAggregateDTO;
import com.mtl.cypw.domain.show.query.ProgramQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/20.
 */
public interface ProgramApi {

    /**
     * 查询演出列表
     * @return
     */
    @PostMapping("/endpoint/program/search/programList")
    TPageResult<ProgramDTO> searchProgramList(@RequestBody QueryRequest<ProgramQuery> request);

    @PostMapping("/endpoint/program/getProgram")
    TSingleResult<ProgramDTO> getProgramById(@RequestBody QueryRequest<ProgramQuery> request);

    /**
     * 后台查询演出列表.
     * @param request 参数
     * @return TPageResult
     */
    @GetMapping("/endpoint/backend/program/search/programList")
    TPageResult<ProgramAggregateDTO> searchBackendProgramList(@RequestBody QueryRequest<ProgramQuery> request);

    /**
     * 后台获取演出详情
     * @param request 参数
     * @return TSingleResult
     */
    @GetMapping("/endpoint/backend/program/program/getProgram")
    TSingleResult<ProgramDTO> getBackendProgramById(@RequestBody QueryRequest<ProgramQuery> request);

}
