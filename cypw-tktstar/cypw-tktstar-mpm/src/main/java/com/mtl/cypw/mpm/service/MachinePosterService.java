package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.MachinePoster;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/16.
 */
public interface MachinePosterService {
    /**
     * 查询企业下所有的海报
     *
     * @param enterpriseId
     * @return
     */
    List<MachinePoster> searchMachinePoster(Integer enterpriseId);
}
