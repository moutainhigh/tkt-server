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
public class SkuUpdateStockParam extends BaseParam {

    private Integer skuId;

    private SkuTypeEnum skuType;

    /**
     * 总库存数更新至（会校验已消费数量,不能小于已消费数量）
     */
    private int stockUpdateTo;
    /**
     * 追加库存数（若为负,则会校验已发数量, 修改后不能小于已发数量）
     */
    private int stockIncrement;

    @Override
    public void checkParam() {

    }
}
