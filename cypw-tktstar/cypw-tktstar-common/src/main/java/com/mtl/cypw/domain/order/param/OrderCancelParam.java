package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.domain.order.enums.OrderCancelEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 取消
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@Builder
public class OrderCancelParam extends BaseParam {

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 取消原因
     */
    private OrderCancelEnum cancelEnum;

    /**
     * 验证用户
     */
    private boolean validationUser;


    @Override
    public void checkParam() {
        ParamChecker.notNull(orderId, "订单编号不能为空");
        ParamChecker.notNull(cancelEnum, "取消原因不能为空");
    }
}
