package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.ProvinceMapper;
import com.mtl.cypw.mpm.model.Province;
import com.mtl.cypw.mpm.service.ProvinceService;
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
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Override
    public List<Province> searchProvinceList() {
        return provinceMapper.selectAll();
    }
}
