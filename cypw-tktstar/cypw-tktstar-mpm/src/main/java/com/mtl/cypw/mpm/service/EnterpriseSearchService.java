package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.EnterpriseSearch;

/**
 * @author tang.
 * @date 2020/1/19.
 */
public interface EnterpriseSearchService {
    /**
     * 查询企业的搜索设置
     *
     * @param enterpriseId 企业ID
     * @return
     */
    EnterpriseSearch getEnterpriseSearch(Integer enterpriseId);
}
