package com.mtl.cypw.stock.service;

import com.mtl.cypw.domain.stock.param.SkuUpdateStockParam;
import com.mtl.cypw.domain.stock.param.StockConsumeWithRecordParam;
import com.mtl.cypw.domain.stock.param.StockRollbackWithRecordParam;
import com.mtl.cypw.stock.model.Stock;
import com.mtl.cypw.stock.model.StockRecord;

/**
 * @author zhengyou.yuan
 * @date 2019-11-29 16:54
 */
public interface StockService {

    Stock create(Stock stock);

    Boolean updateTotalStack(SkuUpdateStockParam param);

    Boolean consumeWithRecord(StockConsumeWithRecordParam param);

    Boolean rollbackWithRecord(StockRollbackWithRecordParam param);

    StockRecord findOneBySerialNo(String serialNo);
}
