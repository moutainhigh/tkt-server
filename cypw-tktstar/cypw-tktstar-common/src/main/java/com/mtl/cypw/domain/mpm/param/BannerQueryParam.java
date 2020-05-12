package com.mtl.cypw.domain.mpm.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.domain.mpm.enums.BannerTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 15:23
 */
@Setter
@Getter
@ToString
public class BannerQueryParam extends BaseParam {

    /**
     * 是否启用，未启用/已启用
     */
    private CommonStateEnum commonState;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 类别（1-演出、2-衍生品）
     */
    private BannerTypeEnum typeEnum;

    @Override
    public void checkParam() {

    }
}
