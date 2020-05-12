package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户侧删除订单
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDeleteParam extends BaseParam {

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 用户ID
     */
    private Integer userId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(orderId, "订单编号不能为空");
    }
}
