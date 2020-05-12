package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.mpm.endpoint.DistrictApi;
import com.mtl.cypw.domain.mpm.dto.DistrictDTO;
import com.mtl.cypw.mpm.model.District;
import com.mtl.cypw.mpm.service.DistrictService;
import com.mtl.cypw.provider.mpm.converter.DistrictConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@RestController
public class DistrictEndpoint implements DistrictApi {

    @Autowired
    DistrictService districtServiceImpl;

    @Autowired
    DistrictConverter districtConverter;

    @Override
    public TMultiResult<DistrictDTO> districts() {
        List<District> list = districtServiceImpl.searchDistrictList();
        return ResultBuilder.succTMulti(districtConverter.toDto(list));
    }
}
