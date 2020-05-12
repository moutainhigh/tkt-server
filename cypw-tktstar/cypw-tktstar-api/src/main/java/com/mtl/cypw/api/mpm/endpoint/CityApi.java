package com.mtl.cypw.api.mpm.endpoint;

import com.juqitech.response.TMultiResult;
import com.mtl.cypw.domain.mpm.dto.CityDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author tang.
 * @date 2019/11/27.
 */
public interface CityApi {


    /**
     * 查询城市列表
     * @return
     */
    @RequestMapping(value = "/endpoint/v1/mpm/citys", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TMultiResult<CityDTO> citys();
}
