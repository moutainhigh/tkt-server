package com.mtl.cypw.mpm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import com.mtl.cypw.mpm.mapper.BuryingPointInfoMapper;
import com.mtl.cypw.mpm.model.BuryingPointInfo;
import com.mtl.cypw.mpm.service.BuryingPointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Service
public class BuryingPointServiceImpl implements BuryingPointService {

    @Resource
    private BuryingPointInfoMapper mapper;

    @Override
    public List<BuryingPointInfo> findBuryingPointInfoList(BuryingPointQueryParam query, Pagination pagination) {
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        List<BuryingPointInfo> list = findBuryingPointInfoList(query);
        pagination.setCount(page.getTotal());
        return list;
    }

    @Override
    public List<BuryingPointInfo> findBuryingPointInfoList(BuryingPointQueryParam query) {
        return mapper.searchBuryingPoint(query);
    }

    @Override
    public void addBuryingPointInfo(BuryingPointInfo buryingPointInfo) {
        if (buryingPointInfo != null && StringUtils.isNotEmpty(buryingPointInfo.getBuryingPointContent())) {
            buryingPointInfo.setCreateTime(DateUtils.now());
            mapper.insertSelective(buryingPointInfo);
        }
    }

    @Override
    public void deleteBuryingPoint(BuryingPointParam param) {
        Example example = new Example(BuryingPointInfo.class);
        Example.Criteria cri = example.createCriteria();
        if (param != null) {
            if (param.getMemberId() != null) {
                cri.andEqualTo("memberId", param.getMemberId());
            }
            if (param.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", param.getEnterpriseId());
            }
            if (param.getBuryingPointType() != null) {
                cri.andEqualTo("buryingPointType", param.getBuryingPointType().getCode());
            }
        }
        cri.andEqualTo("deleted", 0);
        example.orderBy("createTime").desc();
        example.isDistinct();
        BuryingPointInfo buryingPointInfo = new BuryingPointInfo();
        buryingPointInfo.setDeleted(1);
        mapper.updateByExampleSelective(buryingPointInfo, example);
    }
}
