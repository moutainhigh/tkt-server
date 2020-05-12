package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.domain.mpm.dto.MachinePosterDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2020/3/16.
 */
public interface MachinePosterApi {
    /**
     * 查询企业下所有的海报
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/machine_posters", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TMultiResult<MachinePosterDTO> searchMachinePoster(@RequestBody IdRequest request);
}
