package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.common.utils.Money;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 礼品卡
 * @author Johnathon.Yuan
 * @date 2019-11-26 11:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderGiftCardParam extends BaseParam {

    /**
     * 礼品卡卡号
     */
    public Integer giftCardId;

    /**
     * 本次扣除金额
     */
    public Money deductionAmount;

    @Override
    public void checkParam() {

    }
}
