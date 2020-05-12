package com.mtl.cypw.stock.repository;

import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.stock.mapper.StockSeatRecordMapper;
import com.mtl.cypw.stock.model.StockSeatRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-01 20:52
 */
@Slf4j
@Component
public class StockSeatRecordRepository implements GenericRepository<Integer, StockSeatRecord> {

    @Autowired
    private StockSeatRecordMapper stockSeatRecordMapper;

    @Override
    public Integer save(StockSeatRecord model) {
        Assert.notNull(model, "StockRecord is null");
        if (model.brandNew()) {
            stockSeatRecordMapper.insertSelective(model);
        } else {
            stockSeatRecordMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public StockSeatRecord findOneByOrderId(Integer orderId) {
        Example example = new Example(StockSeatRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        return stockSeatRecordMapper.selectOneByExample(example);
    }

}
