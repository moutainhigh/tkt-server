package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 23:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UniformSeatTicketParam extends BaseParam {

    /**
     * 座位票价ID
     */
    private Integer ticketId;
    /**
     * 票档下的座位信息
     */
    private List<UniformSeatParam> seats;

    @Override
    public void checkParam() {

    }
}
