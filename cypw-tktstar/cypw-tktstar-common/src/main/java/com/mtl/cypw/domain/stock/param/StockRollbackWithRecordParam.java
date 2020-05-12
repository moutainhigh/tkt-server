package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-29 18:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StockRollbackWithRecordParam extends BaseParam {

    private Integer orderId;

    private String serialNo;

    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(serialNo, "serialNo 不能为空");
        ParamChecker.notNull(enterpriseId, "enterpriseId 不能为空");
    }


}
