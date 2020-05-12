package com.mtl.cypw.mpm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.mpm.param.EnterpriseQueryParam;
import com.mtl.cypw.mpm.mapper.EnterpriseMapper;
import com.mtl.cypw.mpm.model.Enterprise;
import com.mtl.cypw.mpm.service.EnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Service
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public Enterprise getEnterpriseById(Integer enterpriseId) {
        log.debug("查询企业信息，enterpriseId:{}", enterpriseId);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        log.debug("查询结果，result:{}", JSONObject.toJSONString(enterprise));
        return enterprise;
    }

    @Override
    public Enterprise searchEnterprise(EnterpriseQueryParam queryParam) {
        Example example = new Example(Enterprise.class);
        Example.Criteria cri = example.createCriteria();
        if (queryParam != null) {
            if (queryParam.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", queryParam.getEnterpriseId());
            }
            if (StringUtils.isNotEmpty(queryParam.getEnterpriseAbbr())) {
                cri.andEqualTo("enterpriseAbbr", queryParam.getEnterpriseAbbr());
            }
            if (StringUtils.isNotEmpty(queryParam.getTenantId())) {
                cri.andEqualTo("fhlId", queryParam.getTenantId());
            } else if (StringUtils.isNotEmpty(queryParam.getAppId())) {
                cri.andEqualTo("appId", queryParam.getAppId());
            } else if (StringUtils.isNotEmpty(queryParam.getDomainName())) {
                cri.andEqualTo("domainName", queryParam.getDomainName());
            }
            return enterpriseMapper.selectOneByExample(example);
        }
        return null;
    }
}
