package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.EnterpriseTemplateMapper;
import com.mtl.cypw.mpm.model.EnterpriseTemplate;
import com.mtl.cypw.mpm.service.EnterpriseTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Service
@Slf4j
public class EnterpriseTemplateServiceImpl implements EnterpriseTemplateService {

    @Autowired
    EnterpriseTemplateMapper enterpriseTemplateMapper;

    @Override
    public EnterpriseTemplate getEnterpriseTemplateByEnterpriseId(Integer enterpriseId) {
        log.debug("查询企业模板信息，enterpriseId:{}", enterpriseId);
        Example example = new Example(EnterpriseTemplate.class);
        Example.Criteria cri = example.createCriteria();
        if (enterpriseId != null) {
            cri.andEqualTo("etId", enterpriseId);
        }
        List<EnterpriseTemplate> list = enterpriseTemplateMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
