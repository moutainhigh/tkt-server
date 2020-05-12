package com.mtl.cypw.show.service;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.show.query.ProgramQuery;
import com.mtl.cypw.show.mapper.ProgramCheckInMapper;
import com.mtl.cypw.show.mapper.ProgramMapper;
import com.mtl.cypw.show.mapper.aggregate.ProgramAggregateMapper;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.pojo.ProgramCheckIn;
import com.mtl.cypw.show.pojo.aggregate.ProgramAggregate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/20.
 */
@Service
@Slf4j
public class ProgramService {

    @Resource
    ProgramMapper programMapper;

    @Resource
    ProgramCheckInMapper programCheckInMapper;
    @Resource
    ProgramAggregateMapper aggregateMapper;

    public List<Program> searchProgramList(ProgramQuery query, Pagination pagination) {
        log.debug("查询演出列表，入参query:{},pagination:{}", JSONObject.toJSONString(query), JSONObject.toJSONString(pagination));
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        Example example = new Example(Program.class);
        Example.Criteria cri = example.createCriteria();
        Example.Criteria c = example.createCriteria();
        cri.andEqualTo("isDelete", 0);
        if (query != null) {
            if (query.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", query.getEnterpriseId());
            }
            if (query.getIsRecommend() != null) {
                cri.andEqualTo("wechatRecommend", query.getIsRecommend());
            }
            if (query.getSaleStatus() != null) {
                cri.andEqualTo("saleStatus", query.getSaleStatus());
            }
            if (CollectionUtils.isNotEmpty(query.getSaleStatusList())) {
                cri.andIn("saleStatus", query.getSaleStatusList());
            }
            if (query.getProgramType() != null) {
                cri.andEqualTo("programTypeId", query.getProgramType());
            }
            if (CollectionUtils.isNotEmpty(query.getProgramTypeList())) {
                cri.andIn("programTypeId", query.getProgramTypeList());
            }
            if (StringUtils.isNotEmpty(query.getLikeName())) {
                c.andLike("programTitle", "%" + query.getLikeName() + "%").orLike("keyValue", "%" + query.getLikeName() + "%");
            }
        }
        example.and(c);
//        example.setOrderByClause("field(program_type_id,85,80,82,83)");
        example.orderBy("wechatSort").asc();
        List<Program> result = programMapper.selectByExample(example);
        pagination.setCount(page.getTotal());
        log.debug("演出列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

    public Program getProgramById(Integer programId) {
        log.debug("查询演出详情，programId:{}", programId);
        Program result = programMapper.selectByPrimaryKey(programId);
        log.debug("演出详情查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }


    public ProgramCheckIn getCheckInConfigByCheckInUserId(Integer programId, Integer checkInUserId) {
        Example example = new Example(ProgramCheckIn.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("programId", programId);
        List<ProgramCheckIn> configs = programCheckInMapper.selectByExample(example);
        for (ProgramCheckIn config : configs) {
            String userIdsStr = config.getCheckinAccountIds();
            List<String> checkUserIds = Splitter.on(",").splitToList(userIdsStr);
            if (CollectionUtils.isNotEmpty(checkUserIds) && checkUserIds.contains(String.valueOf(checkInUserId))) {
                return config;
            }
        }
        return null;
    }

    /**
     * 后台查询演出列表.
     * @param param      前段参数
     * @param pagination 分页参数
     * @return 列表
     */
    public List<ProgramAggregate> searchBackendProgramList(ProgramQuery param, Pagination pagination) {
        log.debug("查询后台演出列表，入参query:{},pagination:{}", JSONObject.toJSONString(param), JSONObject.toJSONString(pagination));
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        List<ProgramAggregate> result = aggregateMapper.getProgramList(param.getEnterpriseId());
        pagination.setCount(page.getTotal());
        log.debug("演出列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 查询核销信息
     * @param programId 演出id.
     * @return ProgramCheckIn列表.
     */
    public List<ProgramCheckIn> getCheckInfoByProgramId(Integer programId) {
        Example example = new Example(ProgramCheckIn.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("programId", programId);
        List<ProgramCheckIn> list = programCheckInMapper.selectByExample(example);
        return list;
    }

    /**
     * 初始化核销列表
     * @return ProgramCheckIn列表
     */
    public List<ProgramCheckIn> initCheckInList() {
        List<ProgramCheckIn> list = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            ProgramCheckIn checkIn = new ProgramCheckIn();
            checkIn.setEntranceName("检票口" + i);
            checkIn.setCheckinCount("1");
            checkIn.setCheckinAccountIds("");
            list.add(checkIn);
        }

        return list;
    }
}
