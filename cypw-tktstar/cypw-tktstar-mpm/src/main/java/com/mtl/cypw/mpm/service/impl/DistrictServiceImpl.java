package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.DistrictMapper;
import com.mtl.cypw.mpm.model.District;
import com.mtl.cypw.mpm.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictMapper DistrictMapper;

    @Override
    public List<District> searchDistrictList() {
        return DistrictMapper.selectAll();
    }
}
