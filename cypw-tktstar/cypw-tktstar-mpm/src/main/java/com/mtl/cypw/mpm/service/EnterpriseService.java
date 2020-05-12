package com.mtl.cypw.mpm.service;

import com.mtl.cypw.domain.mpm.param.EnterpriseQueryParam;
import com.mtl.cypw.mpm.model.Enterprise;

/**
 * @author tang.
 * @date 2019/11/26.
 */
public interface EnterpriseService {

    /**
     * 查询企业信息
     *
     * @param enterpriseId 企业id
     * @return
     */
    Enterprise getEnterpriseById(Integer enterpriseId);


    /**
     * 查询企业信息
     *
     * @return
     */
    Enterprise searchEnterprise(EnterpriseQueryParam queryParam);
}
