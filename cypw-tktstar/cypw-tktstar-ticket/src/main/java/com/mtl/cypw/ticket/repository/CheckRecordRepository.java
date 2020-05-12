package com.mtl.cypw.ticket.repository;

import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.ticket.mapper.CheckRecordMapper;
import com.mtl.cypw.ticket.model.CheckRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-13 13:25
 */
@Slf4j
@Component
public class CheckRecordRepository implements GenericRepository<Integer, CheckRecord> {

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Override
    public Integer save(CheckRecord model) {
        Assert.notNull(model, "CheckRecord is null");
        if (model.brandNew()) {
            checkRecordMapper.insertSelective(model);

        } else {
            checkRecordMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }
}
