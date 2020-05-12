package com.mtl.cypw.mpm.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import com.mtl.cypw.mpm.model.BuryingPointInfo;

import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
public interface BuryingPointService {
    List<BuryingPointInfo> findBuryingPointInfoList(BuryingPointQueryParam query, Pagination pagination);

    List<BuryingPointInfo> findBuryingPointInfoList(BuryingPointQueryParam query);

    void addBuryingPointInfo(BuryingPointInfo buryingPointInfo);

    void deleteBuryingPoint(BuryingPointParam param);
}
