package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.CityMapper;
import com.mtl.cypw.mpm.model.City;
import com.mtl.cypw.mpm.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@Service
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper CityMapper;

    @Override
    public List<City> searchCityList() {
        return CityMapper.selectAll();
    }
}
