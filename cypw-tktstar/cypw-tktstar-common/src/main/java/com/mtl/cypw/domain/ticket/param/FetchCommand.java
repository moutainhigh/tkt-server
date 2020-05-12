package com.mtl.cypw.domain.ticket.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-20 14:48
 */
@Data
public abstract class FetchCommand extends BaseParam {

    /**
     * 凭证类型（1：票品, 2：衍生品）
     */
    private Integer voucherType;

    /**
     * 操作类型
     */
    private Integer handleType;

    /**
     * 取票（货）渠道
     */
    private Integer fetchMethod;

    /**
     * 取票（货）码【查询】
     */
    private String fetchCode;

    /**
     * 操作账号ID
     */
    private Integer fetchUserId;

    /**
     * 企业标识
     */
    private Integer enterpriseId;

}
