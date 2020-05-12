package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SKU 库存更新
 * @author Johnathon.Yuan
 * @date 2019-11-29 12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StockConsumeSkuInfoParam extends BaseParam {

    private Integer skuId;

    private SkuTypeEnum skuType;

    private Integer count;


    @Override
    public void checkParam() {

    }
}
