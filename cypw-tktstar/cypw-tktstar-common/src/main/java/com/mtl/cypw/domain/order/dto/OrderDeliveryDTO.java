package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.IdentityTypeEnum;
import com.mtl.cypw.domain.ticket.enums.FetchStatusEnum;
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
public class OrderDeliveryDTO extends BaseEntityInfo {
    /**
     * ID
     */
    private Integer deliveryId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 交付方式()
     */
    private DeliverTypeEnum deliverType;

    /**
     * 交付状态
     */
    private DeliverStatusEnum deliveryStatus;

    /**
     * 是否需要证件取件(0-不需要, 1-需要)
     */
    private Boolean needIdcard;

    /**
     * 证件号类型(1-身份证, 2-护照)
     */
    private IdentityTypeEnum idcardType;

    /**
     * 收件人证件号
     */
    private String addresseeIdcard;

    /**
     * 省份名
     */
    private String provinceName;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 区县名
     */
    private String districtName;

    /**
     * 收件人具体地址
     */
    private String detailedAddress;

    /**
     * 收件人邮编
     */
    private String postCode;

    /**
     * 收件人名
     */
    private String addresseeName;

    /**
     * 收件人电话
     */
    private String addresseeMobile;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 快递费用
     */
    private Money expressFee;

    /**
     * 开始配送时间
     */
    private Date deliverTime;

    /**
     * 配送完成时间
     */
    private Date deliveredTime;

    /**
     *  现场取票取票地点
     */
    private String localeAddress;

    /**
     * 现场取票联系人
     */
    private String localeContact;

    /**
     * 订单取票状态
     */
    private FetchStatusEnum fetchStatus;

    /**
     * 订单取票时间
     */
    private Date fetchedTime;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
