package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.BannerDTO;
import com.mtl.cypw.domain.mpm.param.BannerQueryParam;
import com.mtl.cypw.domain.mpm.param.BannerSaveParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 11:53
 */
public interface BannerApi {

    /**
     * Banner 的 RPC 服务本地 Bean 名称
     */
    String BANNER_API = "BannerEndpoint";

    /**
     * 创建 Banner
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/mpm/banner/create", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<BannerDTO> create(@RequestBody GenericRequest<BannerSaveParam> request);

    /**
     * 更新 Banner
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/mpm/banner/update", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<BannerDTO> update(@RequestBody GenericRequest<BannerSaveParam> request);

    /**
     * 删除 Banner
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/mpm/banner/delete_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> deleteById(@RequestBody IdRequest request);

    /**
     * 根据 ID 查询详情
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/mpm/banner/find_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<BannerDTO> findById(@RequestBody IdRequest request);

    /**
     * 分页查询列表
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/mpm/banner/page_query", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TMultiResult<BannerDTO> pageQuery(@RequestBody QueryRequest<BannerQueryParam> request);

}
