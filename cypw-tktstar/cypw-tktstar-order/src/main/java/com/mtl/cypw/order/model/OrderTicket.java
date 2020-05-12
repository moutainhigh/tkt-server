package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_ticket")
public class OrderTicket extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单明细ID
     */
    @Column(name = "order_item_id")
    private Integer orderItemId;

    /**
     * 区域ID
     */
    @Column(name = "zone_id")
    private Integer zoneId;

    /**
     * 分区名称
     */
    @Column(name = "zone_name")
    private String zoneName;

    /**
     * 座位ID
     */
    @Column(name = "seat_id")
    private Integer seatId;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 座位名称(row_name+col_name)
     */
    @Column(name = "seat_name")
    private String seatName;

    /**
     * 票品ID
     */
    @Column(name = "price_id")
    private Integer priceId;

    /**
     * 每份数量(普通票价固定为1, 套票为动态设置)
     */
    @Column(name = "package_number")
    private Integer packageNumber;

    /**
     * 平均价
     */
    @Column(name = "average_price")
    private Long averagePrice;

    /**
     * 票面价
     */
    @Column(name = "ticket_price")
    private Long ticketPrice;

    /**
     * 市场价
     */
    @Column(name = "origin_price")
    private Long originPrice;

    /**
     * 电子票检票码
     */
    @Column(name = "check_code")
    private String checkCode;

    /**
     * 电子票检票二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 检票状态(0-未检票, 1-已检票, 2-已完成)
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 最后检票时间
     */
    @Column(name = "check_time")
    private Date checkTime;

    /**
     * 最后检票渠道
     */
    @Column(name = "check_channel")
    private Integer checkChannel;

    /**
     * 实名制购票ID
     */
    @Column(name = "order_identification_id")
    private Integer orderIdentificationId;

    /**
     * 票品描述
     */
    @Column(name = "ticket_desc")
    private String ticketDesc;

    /**
     * 检票码来源(0-自主售卖, 1-线下导入)
     */
    @Column(name = "code_source")
    private Integer codeSource;

    /**
     * 打票状态(0-未打票,1-已打票,2-重打票待审批,3-待重打,4-已重打
     */
    @Column(name = "print_status")
    private Integer printStatus;

    /**
     * 重打次数
     */
    @Column(name = "repeat_print_count")
    private Integer repeatPrintCount;

    /**
     * 芯片TID
     */
    @Column(name = "chip_tid")
    private String chipTid;

    /**
     * 取票状态
     */
    @Column(name = "fetch_status")
    private Integer fetchStatus;

    /**
     * 取票时间
     */
    @Column(name = "fetched_time")
    private Date fetchedTime;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }
}