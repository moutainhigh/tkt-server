package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.stock.enums.ReserveSeatStatusEnum;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 13:49
 */
@Data
public class ReserveSeatQuerySpec extends BaseParam {

    /**
     * 预留ID
     */
    private Integer reserveId;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 票价ID
     */
    private Integer priceId;

    /**
     * 座位预留状态
     */
    private ReserveSeatStatusEnum reserveSeatStatus;

    /**
     * 座位列表
     */
    private List<Integer> seatIds;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {

    }
}
