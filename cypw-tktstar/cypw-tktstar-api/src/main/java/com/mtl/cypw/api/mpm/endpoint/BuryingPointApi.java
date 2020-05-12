package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.BuryingPointDTO;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/1/19.
 */
public interface BuryingPointApi {

    /**
     * 查询埋点信息列表（分页）
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/find/burying_point/page")
    TPageResult<BuryingPointDTO> findBuryingPointPageList(@RequestBody QueryRequest<BuryingPointQueryParam> request);

    /**
     * 查询埋点信息列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/find/burying_point")
    TMultiResult<BuryingPointDTO> findBuryingPointList(@RequestBody QueryRequest<BuryingPointQueryParam> request);

    /**
     * 新增埋点信息
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/add/burying_point")
    TSingleResult<Boolean> addBuryingPoint(@RequestBody GenericRequest<BuryingPointParam> request);

    /**
     * 批量删除（企业id，用户id，埋点类型）
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/delete/burying_point")
    TSingleResult<Boolean> deleteBuryingPoint(@RequestBody GenericRequest<BuryingPointParam> request);
}
