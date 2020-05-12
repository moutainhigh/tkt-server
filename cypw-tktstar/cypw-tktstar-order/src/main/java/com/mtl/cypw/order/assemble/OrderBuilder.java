package com.mtl.cypw.order.assemble;

import com.google.common.collect.Lists;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.domain.stock.param.StockConsumeSkuInfoParam;
import com.mtl.cypw.domain.stock.param.StockConsumeWithRecordParam;
import com.mtl.cypw.order.model.Order;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 订单操作装配
 * @author Johnathon.Yuan
 * @date 2019-12-01 23:20
 */
public class OrderBuilder {

    /**
     * 库存消耗记录组装
     * @param order
     * @return
     */
    public static StockConsumeWithRecordParam buildStockConsumeRecord(Order order) {
        StockConsumeWithRecordParam  param = new StockConsumeWithRecordParam();
        param.setOrderId(order.getId());
        param.setSerialNum(order.getOrderNo());
        param.setMemberId(order.getMemberId());
        param.setEnterpriseId(order.getEnterpriseId());
        List<StockConsumeSkuInfoParam> skuInfoParams = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(order.getOrderItems())) {
            skuInfoParams = Lists.transform(order.getOrderItems(), orderItem -> {
                StockConsumeSkuInfoParam skuInfo = new StockConsumeSkuInfoParam();
                skuInfo.setSkuType(SkuTypeEnum.getObject(orderItem.getSkuType()));
                skuInfo.setSkuId(orderItem.getPriceId());
                skuInfo.setCount(orderItem.getQuantity());
                return skuInfo;
            });
        }
        param.setStockConsumeSkuInfoParams(skuInfoParams);
        return param;
    }
}
