package com.mtl.cypw.domain.auth.dto.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/16.
 */
@Data
public class CmbCorpInfo {
    /**
     * 商户号
     */
    @JSONField(ordinal = 0)
    private String corpNo;
    /**
     * 商户名称
     */
    @JSONField(ordinal = 1)
    private String corpName;
    /**
     * APP名称
     */
    @JSONField(ordinal = 2)
    private String appName;
    /**
     * APP版本号
     */
    @JSONField(ordinal = 3)
    private String appVersion;
    /**
     * 用户ID，由身份证+姓名生成
     */
    @JSONField(ordinal = 4)
    private String uniqueUserID;
    /**
     * 用户ID，由一网通ID生成
     */
    @JSONField(ordinal = 5)
    private String expandUserID;
    /**
     * 时间戳
     */
    @JSONField(ordinal = 6)
    private String tokenTimeStamp;

}
