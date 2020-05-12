package com.mtl.cypw.domain.mall.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 18:49
 */
@Data
public class MerchandiseQueryParam extends BaseParam {

    /**
     * 商品类型
     */
    private Integer typeId;

    /**
     * 商品 ID
     */
    private Integer merchandiseId;

    /**
     * 企业 ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(enterpriseId, "参数错误");
    }
}
