package com.mtl.cypw.ticket.repository;

import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.ticket.mapper.CheckStatsMapper;
import com.mtl.cypw.ticket.model.CheckStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-13 13:25
 */
@Slf4j
@Component
public class CheckStatsRepository implements GenericRepository<Integer, CheckStats> {

    @Autowired
    private CheckStatsMapper checkStatsMapper;

    @Override
    public Integer save(CheckStats model) {
        Assert.notNull(model, "CheckStats is null");
        if (model.brandNew()) {
            checkStatsMapper.insertSelective(model);
        } else {
            checkStatsMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public boolean increaseCheckCount(Integer ticketId, Integer enterpriseId) {
        CheckStats stats = findOneByTicketId(ticketId);
        if (stats == null) {
            stats = new CheckStats();
            stats.setCheckCount(1);
            stats.setTicketId(ticketId);
            stats.setEnterpriseId(enterpriseId);
        } else {
            stats.increase();
        }
        this.save(stats);
        return true;
    }

    public CheckStats findOneByTicketId(Integer ticketId) {
        Example example = new Example(CheckStats.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ticketId", ticketId);
        return checkStatsMapper.selectOneByExample(example);
    }

}
