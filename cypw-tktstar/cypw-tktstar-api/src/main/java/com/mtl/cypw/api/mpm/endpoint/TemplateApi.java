package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.TemplateDTO;
import com.mtl.cypw.domain.mpm.param.TemplateQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/4.
 */
public interface TemplateApi {
    /**
     * 根据id查询模板
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/template/getTemplateById")
    TSingleResult<TemplateDTO> getTemplateById(@RequestBody IdRequest request);

    /**
     * 查询模板列表
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/mpm/template/getTemplateList")
    TMultiResult<TemplateDTO> getTemplateList(@RequestBody QueryRequest<TemplateQueryParam> request);
}
