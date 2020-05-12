package com.mtl.cypw.domain.mall.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 18:49
 */
@Data
public class ShoppingCartParam extends BaseParam {

    /**
     * 用户 ID
     */
    private Integer memberId;

    /**
     * 商品 ID
     */
    private Integer productId;

    /**
     * SKU 类型
     */
    private Integer skuType;

    /**
     * SKU ID
     */
    private Integer skuId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 是否覆盖
     */
    private Boolean hasCover;

    /**
     * 企业 ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(quantity, "购买数量错误");
    }
}
