package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 15:52
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderTicketDTO extends BaseEntityInfo {

    /**
     * 自增主键
     */
    private Integer orderTicketId;

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
     * 座位名称(row_name+col_name)
     */
    private String seatName;

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
     * 检票状态(0-未检票, 1-已检票)
     */
    private Boolean checkStatus;

    /**
     * 检票时间
     */
    private Date checkTime;

    /**
     * 检票渠道()
     */
    private Integer checkChannel;

    /**
     * 实名制购票ID
     */
    private Integer orderIdentificationId;

    /**
     * 票品描述
     */
    private String ticketDesc;

    /**
     * 检票码来源(0-自主售卖, 1-线下导入)
     */
    private CodeSourceEnum codeSource;

    /**
     * 打票状态(0-未打票,1-已打票,2-重打票待审批,3-待重打,4-已重打
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
