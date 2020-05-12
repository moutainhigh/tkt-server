package com.mtl.cypw.domain.show.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-06 16:28
 */
@Data
@Builder
public class SeatLockSpec extends BaseParam {

    private Integer lockId;

    private Integer eventId;

    private SeatSellTypeEnum sellType;

    private List<Integer> seatIds;

    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(lockId, "锁座记录不能为空");
        ParamChecker.notEmpty(seatIds, "锁座列表不能为空");
    }
}
