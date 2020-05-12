package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Builder;
import lombok.Data;

/**
 * 配票
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@Builder
public class OrderTicketParam extends BaseParam {

    /**
     * 订单ID
     */
    private Integer orderId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(orderId, "订单编号不能为空");
    }
}
