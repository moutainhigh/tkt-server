package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.mpm.endpoint.ProvinceApi;
import com.mtl.cypw.domain.mpm.dto.ProvinceDTO;
import com.mtl.cypw.mpm.model.Province;
import com.mtl.cypw.mpm.service.ProvinceService;
import com.mtl.cypw.provider.mpm.converter.ProvinceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@RestController
public class ProvinceEndpoint implements ProvinceApi {

    @Autowired
    ProvinceService provinceServiceImpl;

    @Autowired
    ProvinceConverter provinceConverter;

    @Override
    public TMultiResult<ProvinceDTO> provinces() {
        List<Province> list = provinceServiceImpl.searchProvinceList();
        return ResultBuilder.succTMulti(provinceConverter.toDto(list));
    }
}
