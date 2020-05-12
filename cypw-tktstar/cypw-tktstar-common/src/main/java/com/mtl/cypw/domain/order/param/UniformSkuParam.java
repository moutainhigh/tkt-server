package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 23:39
 */
@Data
public class UniformSkuParam extends BaseParam {

    /**
     * sku id
     */
    private Integer skuId;
    /**
     * sku 类型
     */
    private SkuTypeEnum type;
    /**
     * 购买数量
     */
    private Integer count;

    @Override
    public void checkParam() {

    }
}
