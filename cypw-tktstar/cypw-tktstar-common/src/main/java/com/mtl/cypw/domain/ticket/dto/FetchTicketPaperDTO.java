package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 22:14
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FetchTicketPaperDTO extends BaseEntityInfo {

    /**
     * 电子票ID
     */
    private Integer ticketId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单明细ID
     */
    private Integer orderItemId;

    /**
     * 区域ID
     */
    private Integer zoneId;

    /**
     * 分区名称
     */
    private String zoneName;

    /**
     * 座位ID
     */
    private Integer seatId;

    /**
     * 座位排号
     */
    private String rowName;

    /**
     * 座位列号
     */
    private String colName;


    /**
     * 票品ID
     */
    private Integer priceId;

    /**
     * 每份数量(普通票价固定为1, 套票为动态设置)
     */
    private Integer packageNumber;

    /**
     * 平均价
     */
    private Money averagePrice;

    /**
     * 票面价
     */
    private Money ticketPrice;

    /**
     * 市场价
     */
    private Money originPrice;

    /**
     * 电子票检票码
     */
    private String checkCode;

    /**
     * 电子票检票二维码
     */
    private String qrCode;

    /**
     * 实名制购票ID
     */
    private Integer orderIdentificationId;

    /**
     * 票品描述
     */
    private String ticketDesc;

    /**
     * 打票状态（0-未打票,1-已打票,2-重打票待审批,3-待重打,4-已重打）
     */
    private Integer printStatus;

    /**
     * 重打次数
     */
    private Integer repeatPrintCount;

    /**
     * 芯片TID
     */
    private String chipTid;

    /**
     * 取票状态
     */
    private Integer fetchStatus;

    /**
     * 取票时间
     */
    private Date fetchedTime;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

}
