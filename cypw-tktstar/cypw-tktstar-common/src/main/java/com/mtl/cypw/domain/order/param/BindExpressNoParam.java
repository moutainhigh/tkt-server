package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

/**
 * @author sq
 * @date 2020-03-04 14:01
 */
@Data
public class BindExpressNoParam extends BaseParam {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 快递单号
     */
    private String expressNo;

    @Override
    public void checkParam() {

    }
}
