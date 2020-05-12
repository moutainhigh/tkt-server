package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.mpm.endpoint.CityApi;
import com.mtl.cypw.domain.mpm.dto.CityDTO;
import com.mtl.cypw.mpm.model.City;
import com.mtl.cypw.mpm.service.CityService;
import com.mtl.cypw.provider.mpm.converter.CityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@RestController
public class CityEndpoint implements CityApi {

    @Autowired
    CityService cityServiceImpl;

    @Autowired
    CityConverter cityConverter;

    @Override
    public TMultiResult<CityDTO> citys() {
        List<City> list = cityServiceImpl.searchCityList();
        return ResultBuilder.succTMulti(cityConverter.toDto(list));
    }
}
