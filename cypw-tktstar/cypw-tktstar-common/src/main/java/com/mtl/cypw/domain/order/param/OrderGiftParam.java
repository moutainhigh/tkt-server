package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.order.enums.SystemGiftTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统下单赠票详情
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderGiftParam extends BaseParam {

    /**
     * 增票类型
     */
    private SystemGiftTypeEnum giftType;
    /**
     * 增票备注
     */
    private String remark;
    /**
     * 票面标识（用于打印票面）
     */
    private String flag;

    @Override
    public void checkParam() {

    }
}
