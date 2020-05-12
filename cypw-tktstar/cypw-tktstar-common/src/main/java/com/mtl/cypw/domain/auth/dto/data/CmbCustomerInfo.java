package com.mtl.cypw.domain.auth.dto.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/16.
 */
@Data
public class CmbCustomerInfo {
    /**
     * 客户姓名
     */
    @JSONField(ordinal = 0)
    private String realName;
    /**
     * 银行预留手机号
     */
    @JSONField(ordinal = 1)
    private String mobile2;
    /**
     * 证件类型
     * 01-身份证，02-护照，03-其他
     */
    @JSONField(ordinal = 2)
    private String idType;
    /**
     * 证件号码
     */
    @JSONField(ordinal = 3)
    private String personalID;
    /**
     * 客户等级
     * 客户所持卡中的最高卡等级LV1-普卡、LV2-金卡、LV3-金葵花、LV4-砖石卡、LV5-私人银行
     */
    @JSONField(ordinal = 4)
    private String level;
}
