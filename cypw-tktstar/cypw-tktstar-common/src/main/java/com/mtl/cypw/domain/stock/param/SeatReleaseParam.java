package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 释放座位
 * @author Johnathon.Yuan
 * @date 2019-11-29 12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatReleaseParam extends BaseParam {

    private Integer showId;

    private Integer orderId;

    private Integer enterpriseId;

    private SeatSellTypeEnum sellType;

    @Override
    public void checkParam() {

    }
}
