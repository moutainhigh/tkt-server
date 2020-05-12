package com.mtl.cypw.stock.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.stock.model.Stock;
import org.apache.ibatis.annotations.Param;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
public interface StockMapper extends BaseMapper<Stock> {

    int consumeStock(@Param("skuType") Integer skuType, @Param("skuId") Integer skuId, @Param("count") Integer count);

    int rollbackStock(@Param("skuType") Integer skuType, @Param("skuId") Integer skuId, @Param("count") Integer count);
}