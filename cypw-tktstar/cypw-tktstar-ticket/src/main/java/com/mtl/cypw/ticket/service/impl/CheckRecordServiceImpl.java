package com.mtl.cypw.ticket.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.ticket.mapper.CheckRecordMapper;
import com.mtl.cypw.ticket.model.CheckRecord;
import com.mtl.cypw.ticket.model.CheckStats;
import com.mtl.cypw.ticket.repository.CheckStatsRepository;
import com.mtl.cypw.ticket.service.CheckRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 23:51
 */
@Slf4j
@Service
public class CheckRecordServiceImpl implements CheckRecordService {

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Autowired
    private CheckStatsRepository checkStatsRepository;


    @Override
    public CheckRecord save(CheckRecord record) {
        if (record.brandNew()) {
            checkRecordMapper.insertSelective(record);
            checkStatsRepository.increaseCheckCount(record.getTicketId(), record.getEnterpriseId());
        } else {
            checkRecordMapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }

    @Override
    public List<CheckRecord> findCheckRecordByQuery(CheckInQueryParam param, Pagination pagination) {
        Example example = new Example(CheckRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (param.getTicketId() != null) {
            criteria.andEqualTo("ticketId", param.getTicketId());
        }
        if (param.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        }
        if (StringUtils.isNotBlank(param.getCheckCode())) {
            criteria.andEqualTo("checkCode", param.getCheckCode());
        }
        if (StringUtils.isNotBlank(param.getMobileNo())) {
            criteria.andEqualTo("mobileNo", param.getMobileNo());
        }
        if (param.getCheckUserId() != null) {
            criteria.andEqualTo("checkUserId", param.getCheckUserId());
        }
        if (param.getCheckDate() != null) {
            criteria.andGreaterThanOrEqualTo("checkTime", DateUtil.getDayBeginTime(param.getCheckDate()));
            criteria.andLessThanOrEqualTo("checkTime", DateUtil.getDayEndTime(param.getCheckDate()));
        }
        example.orderBy("id").desc();
        Page page = null;
        if (pagination != null) {
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        }
        List<CheckRecord> records = checkRecordMapper.selectByExample(example);
        if (page != null) {
            pagination.setCount(page.getTotal());
        }
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records;
    }

    @Override
    public int findCheckCountByTicketId(Integer ticketId) {
        CheckStats stats = checkStatsRepository.findOneByTicketId(ticketId);
        if (stats == null) {
            return 0;
        }
        return stats.getCheckCount();
    }

    @Override
    public int findCheckCountByTicketIdAndCheckEntryId(Integer ticketId, Integer checkEntryId) {
        CheckInQueryParam param = new CheckInQueryParam();
        param.setTicketId(ticketId);
        param.setCheckEntryId(checkEntryId);
        List<CheckRecord> records = findCheckRecordByQuery(param, null);
        return CollectionUtils.isNotEmpty(records) ? records.size() : 0;
    }

    @Override
    public CheckRecord findLastCheckRecordByTicketId(Integer ticketId) {
        CheckInQueryParam param = new CheckInQueryParam();
        param.setTicketId(ticketId);
        List<CheckRecord> records = findCheckRecordByQuery(param, null);
        return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
    }
}
