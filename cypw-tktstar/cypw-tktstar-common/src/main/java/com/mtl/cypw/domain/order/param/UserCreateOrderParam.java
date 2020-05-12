package com.mtl.cypw.domain.order.param;

import com.juqitech.service.enums.PlatformSource;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.IdentityTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreateOrderParam extends UniformCreateOrderParam {

    /**
     * 下单平台
     */
    private PlatformSource src;

    /**
     * 下单用户IP
     */
    private String clientIp;

    /**
     * 订单总金额
     */
    private Money totalPrice;

    /**
     * 订单商品价值总金额
     */
    private Money orderAmount;

    /**
     * 下单用户ID
     */
    private Integer memberId;

    /**
     * 下单手机号
     */
    private String mobileNo;

    /**
     * 微信APPID（保留）
     */
    private String weChatAppId;

    /**
     * 微信OPENID（保留）
     */
    private String weChatOpenId;

    /**
     * 下单类型
     */
    private OrderTypeEnum orderType;

    /**
     * 交付方式
     */
    private DeliverTypeEnum deliverType;

    /**
     * 接收人姓名
     */
    private String recipientName;

    /**
     * 接收人手机
     */
    private String recipientMobile;

    /**
     * 接收人证件号
     */
    private String recipientIdNo;

    /**
     * 证件号类型
     */
    private IdentityTypeEnum identityType;

    /**
     * 收货地址ID
     */
    private Integer recipientAddressId;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 场次ID
     */
    private Integer showId;

    /**
     * 商户ID
     */
    private Integer enterpriseId;

    /**
     * 优惠使用情况
     */
    private List<OrderCouponParam> orderCouponParams;

    @Override
    public void checkParam() {

    }
}
