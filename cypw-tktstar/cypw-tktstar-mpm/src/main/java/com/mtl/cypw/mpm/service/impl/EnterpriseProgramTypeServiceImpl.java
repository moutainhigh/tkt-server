package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.EnterpriseProgramTypeMapper;
import com.mtl.cypw.mpm.model.EnterpriseProgramType;
import com.mtl.cypw.mpm.service.EnterpriseProgramTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Service
@Slf4j
public class EnterpriseProgramTypeServiceImpl implements EnterpriseProgramTypeService {

    @Autowired
    EnterpriseProgramTypeMapper mapper;

    @Override
    public List<EnterpriseProgramType> getEnterpriseProgramType(Integer enterpriseId) {
        log.debug("查询企业的演出类型列表,enterpriseId:{}", enterpriseId);
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(EnterpriseProgramType.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("deleted", 0);
        cri.andEqualTo("enterpriseId", enterpriseId);
        example.orderBy("sortOrder").asc();
        return mapper.selectByExample(example);
    }
}
