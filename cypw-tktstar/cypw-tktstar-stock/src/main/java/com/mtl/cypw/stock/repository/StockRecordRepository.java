package com.mtl.cypw.stock.repository;

import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.stock.mapper.StockRecordMapper;
import com.mtl.cypw.stock.model.StockRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 17:27
 */
@Slf4j
@Component
public class StockRecordRepository implements GenericRepository<Integer, StockRecord> {

    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public Integer save(StockRecord model) {
        Assert.notNull(model, "StockRecord is null");
        if (model.brandNew()) {
            stockRecordMapper.insertSelective(model);
        } else {
            stockRecordMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public StockRecord findOneBySerialNo(String serialNo) {
        StockRecord spec = new StockRecord();
        spec.setSerialNo(serialNo);
       return stockRecordMapper.selectOne(spec);
    }


}
