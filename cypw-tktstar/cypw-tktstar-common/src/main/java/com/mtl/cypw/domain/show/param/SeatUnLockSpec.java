package com.mtl.cypw.domain.show.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-06 16:28
 */
@Data
@Builder
public class SeatUnLockSpec extends BaseParam {

    private Integer lockId;

    private SeatSellTypeEnum sellType;

    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(lockId, "锁座记录不能为空");
    }
}
