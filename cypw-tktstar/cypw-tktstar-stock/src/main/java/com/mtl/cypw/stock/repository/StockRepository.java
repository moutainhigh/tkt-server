package com.mtl.cypw.stock.repository;

import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.stock.mapper.StockMapper;
import com.mtl.cypw.stock.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 17:27
 */
@Slf4j
@Component
public class StockRepository implements GenericRepository<Integer, Stock> {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Integer save(Stock model) {
        Assert.notNull(model, "stock is null");
        if (model.brandNew()) {
            stockMapper.insertSelective(model);
        } else {
            stockMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public Stock create(Stock stock) {
        this.save(stock);
        return stock;
    }

    public Stock findOneBySkuIdAndType(SkuTypeEnum skuType, Integer skuId) {
        Example example = new Example(Stock.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuType", skuType.getCode());
        criteria.andEqualTo("skuId", skuId);
        return stockMapper.selectOneByExample(example);
    }

    public List<Stock> findOneBySkuIdAndType(SkuTypeEnum skuType, List<Integer> skuIds) {
        Example example = new Example(Stock.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuType", skuType.getCode());
        criteria.andIn("skuId", skuIds);
        return stockMapper.selectByExample(example);
    }

    public int consumeStock(SkuTypeEnum skuType, Integer skuId, int count) {
        return stockMapper.consumeStock(skuType.getCode(), skuId, count);
    }

    public int rollbackStock(SkuTypeEnum skuType, Integer skuId, int count) {
        return stockMapper.rollbackStock(skuType.getCode(), skuId, count);
    }

}
