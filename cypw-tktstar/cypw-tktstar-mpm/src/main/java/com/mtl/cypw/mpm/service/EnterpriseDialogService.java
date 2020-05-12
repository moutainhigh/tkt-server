package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.EnterpriseDialog;

/**
 * @author tang.
 * @date 2020/02/18.
 */
public interface EnterpriseDialogService {

    /**
     * 查询企业首页弹出窗
     *
     * @param enterpriseId
     * @param isShow
     * @return
     */
    EnterpriseDialog getEnterpriseDialog(Integer enterpriseId, Integer isShow);

}
