package com.mtl.cypw.domain.show.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-06 16:28
 */
@Data
public class SeatQuerySpec extends BaseParam {

    private Integer eventId;

    private Integer zoneId;

    private Integer priceId;

    private Boolean isOnline;

    private Integer lockId;

    private Integer reserveId;

    private List<Integer> seatIds;

    private List<Integer> seatStatusList;

    @Override
    public void checkParam() {

    }
}
