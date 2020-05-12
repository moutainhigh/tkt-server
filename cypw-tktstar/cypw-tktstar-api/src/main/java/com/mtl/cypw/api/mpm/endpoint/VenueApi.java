package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.VenueDTO;
import com.mtl.cypw.domain.mpm.param.VenueQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2019/12/12.
 */
public interface VenueApi {

    /**
     * 查询场馆信息
     *
     * @param request 场馆ID
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/get/venue", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<VenueDTO> getVenueById(@RequestBody IdRequest request);

    /**
     * 查询场馆列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/get/venues", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TMultiResult<VenueDTO> getVenueList(@RequestBody QueryRequest<VenueQueryParam> request);
}
