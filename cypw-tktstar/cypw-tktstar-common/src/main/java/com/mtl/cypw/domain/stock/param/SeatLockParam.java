package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 锁定座位
 * @author Johnathon.Yuan
 * @date 2019-11-29 12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatLockParam extends BaseParam {

    private Integer orderId;

    private Integer eventId;

    private SeatSellTypeEnum sellType;

    private Integer memberId;

    private Integer enterpriseId;

    private Set<Integer> seatIds;

    @Override
    public void checkParam() {

    }
}
