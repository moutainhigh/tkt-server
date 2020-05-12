package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 支付
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPaidParam extends BaseParam {

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单支付金额
     */
    private Money orderPrice;

    /**
     * 支付方式
     */
    private PayTypeEnum payType;

    /**
     * 支付流水号
     */
    private String serialNo;

    /**
     * 支付成功时间
     */
    private Date paySuccessTime;


    @Override
    public void checkParam() {
        ParamChecker.notNull(orderId, "订单编号不能为空");
    }
}
