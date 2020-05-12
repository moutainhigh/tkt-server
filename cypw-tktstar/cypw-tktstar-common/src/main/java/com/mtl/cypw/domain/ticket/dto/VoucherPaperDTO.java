package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-19 11:24
 */
@Setter
@Getter
@ToString(callSuper = true)
public class VoucherPaperDTO extends BaseEntityInfo {

    /**
     * 凭证
     */
    private Integer voucherId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 取货/取票流水号
     */
    private String voucherNo;

    /**
     * 凭证类型（1：票品, 2：衍生品）
     */
    private Integer voucherType;

    /**
     * 凭证描述
     */
    private String voucherDesc;

    /**
     * 操作类型（1：正常取票/货），2：重新取票/货）
     */
    private Integer handleType;

    /**
     * 方式（1：自助取票机，2：线下打印机，3：小程序提货）
     */
    private Integer fetchMethod;

    /**
     * 设备ID
     */
    private Integer machineId;

    /**
     * 设备 mac 地址
     */
    private String macId;

    /**
     * 取票身份证号
     */
    private String idCard;

    /**
     * 取票身份证名称
     */
    private String idCardName;

    /**
     * 取票（货）锁定时间
     */
    private Date fetchLockTime;

    /**
     * 取票（货）完成时间
     */
    private Date fetchCompleteTime;

    /**
     * 取票（货）码
     */
    private String fetchCode;

    /**
     * 操作人ID
     */
    private Integer fetchUserId;

    /**
     * 操作人姓名
     */
    private String fetchUserName;

    /**
     * 商品总件数
     */
    private Integer quantity;

    /**
     * 凭证状态（1：取货锁定中，2：取货完成）
     */
    private Integer status;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 商品明细
     */
    List<FetchItemPaperDTO> itemPapers;

    /**
     * 订单票券
     */
    List<FetchTicketPaperDTO> ticketPapers;

}
