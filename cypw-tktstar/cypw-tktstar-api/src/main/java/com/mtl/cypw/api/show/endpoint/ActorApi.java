package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.domain.show.dto.ActorDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/11/27.
 */
public interface ActorApi {


    /**
     * 查询项目演职人员列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/program/search/actors")
    TMultiResult<ActorDTO> searchActorList(@RequestBody IdRequest request);
}
