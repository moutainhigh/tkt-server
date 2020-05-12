package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.TemplateApi;
import com.mtl.cypw.domain.mpm.dto.TemplateDTO;
import com.mtl.cypw.domain.mpm.param.TemplateQueryParam;
import com.mtl.cypw.mpm.model.Template;
import com.mtl.cypw.mpm.service.TemplateService;
import com.mtl.cypw.provider.mpm.converter.TemplateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Slf4j
@RestController
public class TemplateEndpoint implements TemplateApi {

    @Resource
    private TemplateService templateServiceImpl;

    @Resource
    private TemplateConverter templateConverter;

    @Override
    public TSingleResult<TemplateDTO> getTemplateById(IdRequest request) {
        Template template = templateServiceImpl.getTemplate(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(templateConverter.toDto(template));
    }

    @Override
    public TMultiResult<TemplateDTO> getTemplateList(QueryRequest<TemplateQueryParam> request) {
        List<Template> templates = templateServiceImpl.getTemplateList(request.getParam());
        return ResultBuilder.succTMulti(templateConverter.toDto(templates));
    }
}
