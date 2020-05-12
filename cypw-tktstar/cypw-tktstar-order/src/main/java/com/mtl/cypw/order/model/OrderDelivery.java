package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
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
@Table(name = "cy_order_delivery")
public class OrderDelivery extends BaseModel {
    /**
     * 自增ID
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
     * 交付方式()
     */
    @Column(name = "deliver_type")
    private Integer deliverType;

    /**
     * 交付状态(0-未配送, 1-配送中, 2-已配送)
     */
    @Column(name = "delivery_status")
    private Integer deliveryStatus;

    /**
     * 是否需要证件取件(0-不需要, 1-需要)
     */
    @Column(name = "need_idcard")
    private Integer needIdcard;

    /**
     * 证件号类型(1-身份证, 2-护照)
     */
    @Column(name = "idcard_type")
    private Integer idcardType;

    /**
     * 收件人证件号
     */
    @Column(name = "addressee_idcard")
    private String addresseeIdcard;

    /**
     * 省份名
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市名
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区县名
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 收件人具体地址
     */
    @Column(name = "detailed_address")
    private String detailedAddress;

    /**
     * 收件人邮编
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 收件人名
     */
    @Column(name = "addressee_name")
    private String addresseeName;

    /**
     * 收件人电话
     */
    @Column(name = "addressee_mobile")
    private String addresseeMobile;

    /**
     * 快递公司
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 快递单号
     */
    @Column(name = "express_no")
    private String expressNo;

    /**
     * 快递费用
     */
    @Column(name = "express_fee")
    private Long expressFee;

    /**
     * 开始配送时间
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 配送完成时间
     */
    @Column(name = "delivered_time")
    private Date deliveredTime;

    /**
     * 现场取票取票地点
     */
    @Column(name = "locale_address")
    private String localeAddress;

    /**
     * 现场取票联系人
     */
    @Column(name = "locale_contact")
    private String localeContact;

    /**
     * 订单取票码
     */
    @Column(name = "fetch_code")
    private String fetchCode;

    /**
     * 订单取票二维码
     */
    @Column(name = "fetch_qrcode")
    private String fetchQrcode;

    /**
     * 订单取票状态
     */
    @Column(name = "fetch_status")
    private Integer fetchStatus;

    /**
     * 订单取票时间
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

    public boolean isDeliveryCompleted() {
        return this.getDeliverType() == DeliverTypeEnum.OFFLINE.getCode()
                || this.getDeliverType() == DeliverTypeEnum.SPOT_PICKING.getCode()
                || this.getDeliverType() == DeliverTypeEnum.E_TICKET.getCode();
    }

    public boolean isUndelivered() {
        return this.getDeliveryStatus() == DeliverStatusEnum.UNDELIVERED.getCode();
    }

    public boolean isDelivering() {
        return this.getDeliveryStatus() == DeliverStatusEnum.DELIVERING.getCode();
    }
}