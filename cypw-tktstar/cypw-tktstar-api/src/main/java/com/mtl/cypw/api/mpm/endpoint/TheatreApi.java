package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.TheatreDTO;
import com.mtl.cypw.domain.mpm.param.TheatreQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/3.
 */
public interface TheatreApi {
    /**
     * 根据id查询剧院
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/theatre/getTheatreById")
    TSingleResult<TheatreDTO> getTheatreById(@RequestBody IdRequest request);

    /**
     * 查询剧院列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/theatre/getTheatreList")
    TMultiResult<TheatreDTO> getTheatreList(@RequestBody QueryRequest<TheatreQueryParam> request);
}
