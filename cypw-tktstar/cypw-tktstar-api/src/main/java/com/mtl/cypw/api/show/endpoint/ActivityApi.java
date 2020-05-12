package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.show.dto.ActivityDTO;
import com.mtl.cypw.domain.show.query.ActivityQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/1/3.
 */
public interface ActivityApi {

    /**
     * 查询单个活动
     * @param request
     * @return
     */
    @PostMapping("/endpoint/show/get/activity")
    TSingleResult<ActivityDTO> getActivity(@RequestBody IdRequest request);

    /**
     * 查询活动列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/show/search/activity")
    TMultiResult<ActivityDTO> searchActivity(@RequestBody QueryRequest<ActivityQuery> request);

    /**
     * 查询活动列表(分页)
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/show/search/activity/page")
    TPageResult<ActivityDTO> searchActivityPage(@RequestBody QueryRequest<ActivityQuery> request);
}
