package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 18:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StockRollbackWithTicketParam extends BaseParam {

    private Integer orderTicketId;

    private SkuTypeEnum skuType;

    private Integer skuId;

    private Integer priceId;

    private Integer seatId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(orderTicketId, "orderTicketId 不能为空");
        ParamChecker.notNull(skuType, "skuType 不能为空");
        ParamChecker.notNull(skuId, "skuId 不能为空");
    }
}
