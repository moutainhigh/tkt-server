package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 18:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StockConsumeWithRecordParam extends BaseParam {

    private Integer orderId;

    private String serialNum;

    private Integer memberId;

    private Integer enterpriseId;

    List<StockConsumeSkuInfoParam> stockConsumeSkuInfoParams;

    @Override
    public void checkParam() {

    }
}
