package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 票及座位
 * @author Johnathon.Yuan
 * @date 2019-11-29 12:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatTicketParam extends BaseParam {

    private Integer seatId;

    private Integer orderTicketId;

    @Override
    public void checkParam() {

    }
}
