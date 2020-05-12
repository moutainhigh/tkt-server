package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统下单操作信息
 * @author Johnathon.Yuan
 * @date 2019-11-26 10:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemRecordParam extends BaseParam {

    /**
     * 操作人ID
     */
    private Integer opId;
    /**
     * 操作人姓名
     */
    private String opName;

    @Override
    public void checkParam() {

    }
}
