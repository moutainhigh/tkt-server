package com.mtl.cypw.domain.mall.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 18:49
 */
@Data
public class ShoppingCartOperateParam extends BaseParam {

    /**
     * 用户 ID
     */
    private Integer memberId;

    /**
     * SKU 类型
     */
    private Integer skuType;

    /**
     * SKU ID
     */
    private List<Integer> skuIds;

    @Override
    public void checkParam() {

    }
}
