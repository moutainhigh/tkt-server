package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 23:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UniformSeatParam extends BaseParam {

    /**
     * 座位ID
     */
    private Integer seatId;
    /**
     * 座位名称(行号:列号)
     */
    private String seatName;

    @Override
    public void checkParam() {

    }
}
