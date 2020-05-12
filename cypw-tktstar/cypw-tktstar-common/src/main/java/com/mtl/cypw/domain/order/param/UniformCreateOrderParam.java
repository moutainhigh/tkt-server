package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.domain.order.enums.ChannelEnum;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:57
 */
@Data
public abstract class UniformCreateOrderParam extends BaseParam {


    protected ChannelEnum channel;

    /**
     * 非选座下单 SKU 明细(包含非选座票品、衍生品)
     */
    protected List<UniformSkuParam> skuRequests;

    /**
     * 选座下单明细(按票档维度分组)
     */
    protected List<UniformSeatTicketParam> uniformSeatRequests;

    @Override
    public void checkParam() {

    }
}
