package com.mtl.cypw.stock.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.stock.enums.StockActionEnum;
import com.mtl.cypw.domain.stock.enums.StockRecordStatusEnum;
import com.mtl.cypw.domain.stock.param.SkuUpdateStockParam;
import com.mtl.cypw.domain.stock.param.StockConsumeSkuInfoParam;
import com.mtl.cypw.domain.stock.param.StockConsumeWithRecordParam;
import com.mtl.cypw.domain.stock.param.StockRollbackWithRecordParam;
import com.mtl.cypw.stock.exception.StockBizException;
import com.mtl.cypw.stock.exception.StockInvalidException;
import com.mtl.cypw.stock.model.Stock;
import com.mtl.cypw.stock.model.StockRecord;
import com.mtl.cypw.stock.repository.StockRecordRepository;
import com.mtl.cypw.stock.repository.StockRepository;
import com.mtl.cypw.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 17:07
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockRecordRepository stockRecordRepository;

    @Override
    public Stock create(Stock stock) {
        return stockRepository.create(stock);
    }

    @Override
    public Boolean updateTotalStack(SkuUpdateStockParam param) {
        Stock stock = stockRepository.findOneBySkuIdAndType(param.getSkuType(), param.getSkuId());
        if (stock == null) {
            log.error("没有找到该商品库存, skuId = {}, skuType = {}", param.getSkuId(), param.getSkuType());
            throw new StockBizException("没有找到该商品库存", ErrorCode.ERROR_ORDER.getCode());
        }
        int totalStock = 0;
        if (param.getStockIncrement() == 0) {
            totalStock = stock.getTotalStock();
        } else {
            totalStock = stock.getTotalStock() + param.getStockIncrement();
        }
        if (totalStock < stock.getSellingStock()) {
            log.error("总库存小于已售库存, current = {}, request = {}", JsonUtils.toJson(stock), JsonUtils.toJson(param));
            throw new StockBizException("总库存小于已售库存", ErrorCode.ERROR_ORDER.getCode());
        }
        stock.setTotalStock(totalStock);
        stockRepository.save(stock);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean consumeWithRecord(StockConsumeWithRecordParam param) {
        Assert.isTrue(param != null && StringUtils.isNotBlank(param.getSerialNum()), "消耗库存参数错误");
        StockRecord record = buildStockRecord(param);
        stockRecordRepository.save(record);
        for (StockConsumeSkuInfoParam skuInfoParam : param.getStockConsumeSkuInfoParams()) {
            stockHandler(skuInfoParam, StockActionEnum.CONSUME);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean rollbackWithRecord(StockRollbackWithRecordParam param) {
        Assert.isTrue(param != null && StringUtils.isNotBlank(param.getSerialNo()), "回滚库存参数错误");
        StockRecord record = this.findOneBySerialNo(param.getSerialNo());
        if (record == null || record.getStatus() == StockRecordStatusEnum.ROLLBACK.getCode()) {
            return true;
        }
        record.setStatus(StockRecordStatusEnum.ROLLBACK.getCode());
        stockRecordRepository.save(record);
        List<StockConsumeSkuInfoParam> skuInfoParams = JsonUtils.toList(record.getSkuInfo(), StockConsumeSkuInfoParam.class);
        if (CollectionUtils.isEmpty(skuInfoParams)) {
            log.error("回滚库存失败, stockRecord = {}", JsonUtils.toJson(record));
            throw new StockBizException("消费库存时记录异常", ErrorCode.ERROR_ORDER.getCode());
        }
        for (StockConsumeSkuInfoParam skuInfoParam : skuInfoParams) {
            stockHandler(skuInfoParam, StockActionEnum.ROLLBACK);
        }
        return true;
    }

    @Override
    public StockRecord findOneBySerialNo(String serialNo) {
        return stockRecordRepository.findOneBySerialNo(serialNo);
    }

    private void stockHandler(StockConsumeSkuInfoParam skuInfoParam, StockActionEnum action) {
        Stock stock = stockRepository.findOneBySkuIdAndType(skuInfoParam.getSkuType(), skuInfoParam.getSkuId());
        if (stock == null) {
            log.error("没有找到该商品库存, skuId = {}, skuType = {}", skuInfoParam.getSkuId(), skuInfoParam.getSkuType());
            throw new StockBizException("没有找到该商品库存", ErrorCode.ERROR_ORDER.getCode());
        }
        int count = skuInfoParam.getCount();
        int totalStock = stock.getTotalStock();
        int affectNum = 0;
        if (StockActionEnum.CONSUME.getCode() == action.getCode()) {
            int sellingStock = stock.getSellingStock() + count;
            if (sellingStock > totalStock) {
                log.error("消耗库存数量超标, skuId = {}, skuType = {}, total = {}, selling = {}, the count = {}",
                        skuInfoParam.getSkuId(), skuInfoParam.getSkuType(), totalStock, stock.getSellingStock(), count);
                throw new StockInvalidException("消耗库存数量超标", ErrorCode.ERROR_ORDER.getCode());
            }
            affectNum = stockRepository.consumeStock(skuInfoParam.getSkuType(), skuInfoParam.getSkuId(), count);
        } else {
            int sellingStock = stock.getSellingStock() - count;
            if (sellingStock > totalStock) {
                log.error("回滚库存数量超标, skuId = {}, skuType = {}, total = {}, selling = {}, the count = {}",
                        skuInfoParam.getSkuId(), skuInfoParam.getSkuType(), totalStock, stock.getSellingStock(), count);
                throw new StockInvalidException("回滚库存数量超标", ErrorCode.ERROR_ORDER.getCode());
            }
            affectNum = stockRepository.rollbackStock(skuInfoParam.getSkuType(), skuInfoParam.getSkuId(), count);
        }
        if (affectNum <= 0) {
            log.error("更新库存失败, skuId = {}, skuType = {}, the count = {}", skuInfoParam.getSkuId(),
                    skuInfoParam.getSkuType(), count);
            throw new StockBizException("库存更新失败", ErrorCode.ERROR_ORDER.getCode());
        }
    }

    private StockRecord buildStockRecord(StockConsumeWithRecordParam param) {
        StockRecord stockRecord = new StockRecord();
        stockRecord.setOrderId(param.getOrderId());
        stockRecord.setSerialNo(param.getSerialNum());
        stockRecord.setMemberId(param.getMemberId());
        stockRecord.setStatus(StockRecordStatusEnum.TRY_LOCK_SUCCESS.getCode());
        stockRecord.setSkuInfo(JsonUtils.toJson(param.getStockConsumeSkuInfoParams()));
        stockRecord.setEnterpriseId(param.getEnterpriseId());
        return stockRecord;
    }

}
