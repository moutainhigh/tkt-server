package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.EnterpriseSearchMapper;
import com.mtl.cypw.mpm.model.EnterpriseSearch;
import com.mtl.cypw.mpm.service.EnterpriseSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Service
@Slf4j
public class EnterpriseSearchServiceImpl implements EnterpriseSearchService {

    @Resource
    private EnterpriseSearchMapper mapper;

    @Override
    public EnterpriseSearch getEnterpriseSearch(Integer enterpriseId) {
        log.debug("查询企业的搜索设置,enterpriseId:{}", enterpriseId);
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(EnterpriseSearch.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("enterpriseId", enterpriseId);
        List<EnterpriseSearch> list = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
