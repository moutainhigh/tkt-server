package com.mtl.cypw.domain.mpm.dto;

import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import lombok.Data;

/**
 * 衍生品商城交付方式
 * @author Johnathon.Yuan
 * @date 2020-03-10 14:16
 */
@Data
public class EnterpriseDeliveryDTO {
    /**
     * 取票方式
     */
    private DeliverTypeEnum deliverType;

    /**
     * 温馨提示（快递公司）
     */
    private String tips;

    /**
     * 配送费
     */
    private Integer expressFee;

    /**
     * 订单免邮金额限制
     */
    private Integer freeShippingRestrict;

    /**
     * 取票地址
     */
    private String fetchAddress;

    /**
     * 联系电话
     */
    private String contactMobile;

    /**
     * 上门取票的服务时间说明
     */
    private String fetchTimeDesc;

}
