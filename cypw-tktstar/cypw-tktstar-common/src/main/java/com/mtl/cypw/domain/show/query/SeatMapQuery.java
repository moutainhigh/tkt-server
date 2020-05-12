package com.mtl.cypw.domain.show.query;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-06 16:28
 */
@Data
public class SeatMapQuery extends BaseParam {

    private Integer eventId;

    private Integer zoneId;

    private Integer priceId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(eventId, "场次ID不能为空");
    }
}
