package com.mtl.cypw.domain.mpm.param;

import com.mtl.cypw.domain.mpm.enums.BuryingPointTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@Data
public class BuryingPointParam {

    /**
     * 埋点类型
     * @see BuryingPointTypeEnum
     */
    private BuryingPointTypeEnum buryingPointType;
    /**
     * 埋点内容
     */
    private String buryingPointContent;
    /**
     * 来源，例：WEIXIN、WEB、M_WEB、APP、WEIXIN_MINI
     */
    private String sourcePlatform;
    /**
     * 来源页面（非必填），例：“首页”“详情页”
     */
    private String sourcePage;

    private Integer memberId;

    private Integer enterpriseId;
}
