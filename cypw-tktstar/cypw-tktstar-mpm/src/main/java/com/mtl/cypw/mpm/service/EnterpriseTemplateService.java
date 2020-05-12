package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.EnterpriseTemplate;

/**
 * @author tang.
 * @date 2019/11/26.
 */
public interface EnterpriseTemplateService {

    /**
     * 查询企业信息
     * @param enterpriseId 企业id
     * @return
     */
    EnterpriseTemplate getEnterpriseTemplateByEnterpriseId(Integer enterpriseId);
}
