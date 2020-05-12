package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;

import java.util.Set;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 11:32
 */
@Data
public class ReserveSeatParam extends BaseParam {

    /**
     * 预留ID
     */
    private Integer reservedId;

    /**
     * 操作座位
     */
    private Set<Integer> seatIds;

    @Override
    public void checkParam() {
        ParamChecker.notNull(reservedId, "预留编号不能为空");
        ParamChecker.notEmpty(seatIds, "座位信息不能为空");
    }
}
