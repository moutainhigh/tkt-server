package com.mtl.cypw.domain.show.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.domain.show.enums.SeatReservedOperationEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 16:28
 */
@Data
@Builder
public class SeatReservedOperationSpec extends BaseParam {

    private Integer eventId;

    private Integer reserveId;

    private SeatReservedOperationEnum operation;

    private Set<Integer> seatIds;

    @Override
    public void checkParam() {
        ParamChecker.notNull(eventId, "场次ID不能为空");
        ParamChecker.notNull(reserveId, "预留ID不能为空");
        ParamChecker.notNull(operation, "预留操作不能为空");
        ParamChecker.notEmpty(seatIds, "预留座位记录不能为空");
    }
}
