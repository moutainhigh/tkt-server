package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 17:27
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FetchPaperResultDTO extends BaseEntityInfo {

    /**
     * 取票（货）渠道
     */
    private Integer fetchMethod;

    /**
     * 取票（货）码【查询】
     */
    private String fetchCode;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 身份证名称
     */
    private String idCardName;

    /**
     * 凭证号
     */
    private String voucherNo;

    /**
     * 结果
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误描述
     */
    private String errorMessage;

    /**
     * 订单列表
     */
    private List<OrderPaperDTO> orderPapers;
}
