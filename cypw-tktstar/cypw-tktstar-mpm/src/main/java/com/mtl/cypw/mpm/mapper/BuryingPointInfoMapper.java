package com.mtl.cypw.mpm.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import com.mtl.cypw.mpm.model.BuryingPointInfo;

import java.util.List;

/**
 * @author tang.
 * @date 2020年01月19日 下午06:16:13
 */
public interface BuryingPointInfoMapper extends BaseMapper<BuryingPointInfo> {

    List<BuryingPointInfo> searchBuryingPoint(BuryingPointQueryParam pointQueryParam);
}