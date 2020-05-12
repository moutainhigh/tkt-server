package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mpm.dto.EnterpriseDTO;
import com.mtl.cypw.domain.mpm.param.EnterpriseQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2019/11/26.
 */
public interface EnterpriseApi {

    /**
     * 获取 enterprise
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/mpm/enterprise/getEnterprise", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<EnterpriseDTO> getEnterprise(@RequestBody QueryRequest<EnterpriseQueryParam> request);
}
