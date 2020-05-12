package com.mtl.cypw.payment.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class AliPayRequest {
    public static final String payUrl = "https://openapi.alipay.com/gateway.do";
    /**
     * 必填，商户订单号
     */
    private String out_trade_no;
    /**
     * 必填，销售产品码，与支付宝签约的产品码名称。注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    private String product_code;
    /**
     * 必填，订单总金额，单位为元，精确到小数点后两位
     */
    private BigDecimal total_amount;
    /**
     * 必填，订单标题
     */
    private String subject;
    /**
     * 订单描述
     */
    private String body;
    /**
     * 绝对超时时间，格式为yyyy-MM-dd HH:mm:ss
     */
    private String time_expire;
    /**
     * 订单包含的商品列表信息，json格式
     */
    private Object goods_detail;
    /**
     * 公用回传参数
     */
    private String passback_params;
    /**
     * 业务扩展参数
     */
    private Object extend_params;
    /**
     * 商品主类型 :0-虚拟类商品,1-实物类商品 , 注：虚拟类商品不支持使用花呗渠道
     */
    private String goods_type;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     */
    private String timeout_express;
    /**
     * 优惠参数 ,  注：仅与支付宝协商后可用
     */
    private String promo_params;
    /**
     * 描述分账信息
     */
    private Object royalty_info;
    /**
     * 间连受理商户信息体，当前只对特殊银行机构特定场景下使用此字段
     */
    private Object sub_merchant;
    /**
     * 商户原始订单号，最大长度限制32位
     */
    private String merchant_order_no;
    /**
     * 可用渠道,用户只能在指定渠道范围内支付，多个渠道以逗号分割
     */
    private String enable_pay_channels;
    /**
     * 商户门店编号
     */
    private String store_id;
    /**
     * 禁用渠道,用户不可用指定渠道支付
     */
    private String disable_pay_channels;
    /**
     * PC扫码支付的方式，支持前置模式和 跳转模式
     */
    private String qr_pay_mode;
    /**
     * 商户自定义二维码宽度
     */
    private Integer qrcode_width;
    /**
     * 描述结算信息
     */
    private Object settle_info;
    /**
     * 开票信息
     */
    private Object invoice_info;
    /**
     * 签约参数，支付后签约场景使用
     */
    private Object agreement_sign_params;
    /**
     * 请求后页面的集成方式。
     */
    private String integration_type;
    /**
     * 请求来源地址
     */
    private String request_from_url;
    /**
     * 商户传入业务信息，具体值要和支付宝约定
     */
    private String business_params;
    /**
     * 外部指定买家
     */
    private Object ext_user_info;

    /**
     * 用户付款中途退出返回商户的网址
     */
    private String quit_url;
}
