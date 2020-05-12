package com.mtl.cypw.domain.payment.callback;

import lombok.Data;

import java.util.Date;

/**
 * ali支付回调参数
 *
 * @author tang.
 * @date 2020/3/9.
 */
@Data
public class AliPayCallbackParam {

    /**
     * 通知时间
     * 是否必填：是
     */
    private Date notify_time;

    /**
     * 通知类型
     * 是否必填：是
     */
    private String notify_type;
    /**
     * 通知校验ID
     * 是否必填：是
     */
    private String notify_id;
    /**
     * 支付宝分配给开发者的应用Id
     * 是否必填：是
     */
    private String app_id;
    /**
     * 支付宝官方文档没有此字段，实际返回有
     * 是否必填：?
     */
    private String auth_app_id;
    /**
     * 编码格式
     * 是否必填：是
     */
    private String charset;
    /**
     * 接口版本
     * 是否必填：是
     */
    private String version;
    /**
     * 签名类型
     * 是否必填：是
     */
    private String sign_type;
    /**
     * 签名
     * 是否必填：是
     */
    private String sign;
    /**
     * 支付宝交易号
     * 是否必填：是
     */
    private String trade_no;
    /**
     * 商户订单号
     * 是否必填：是
     */
    private String out_trade_no;
    /**
     * 商户业务号
     * 是否必填：否
     */
    private String out_biz_no;
    /**
     * 买家支付宝用户号
     * 是否必填：否
     */
    private String buyer_id;
    /**
     * 买家支付宝账号
     * 是否必填：否
     */
    private String buyer_logon_id;
    /**
     * 卖家支付宝用户号
     * 是否必填：否
     */
    private String seller_id;
    /**
     * 卖家支付宝账号
     * 是否必填：否
     */
    private String seller_email;
    /**
     * 交易状态
     * 是否必填：否
     */
    private String trade_status;
    /**
     * 订单金额
     * 是否必填：否
     */
    private String total_amount;
    /**
     * 实收金额
     * 是否必填：否
     */
    private String receipt_amount;
    /**
     * 开票金额
     * 是否必填：否
     */
    private String invoice_amount;
    /**
     * 付款金额
     * 是否必填：否
     */
    private String buyer_pay_amount;
    /**
     * 集分宝金额
     * 是否必填：否
     */
    private String point_amount;
    /**
     * 总退款金额
     * 是否必填：否
     */
    private String refund_fee;
    /**
     * 订单标题
     * 是否必填：否
     */
    private String subject;
    /**
     * 商品描述
     * 是否必填：否
     */
    private String body;
    /**
     * 交易创建时间
     * 是否必填：否
     */
    private Date gmt_create;
    /**
     * 交易付款时间
     * 是否必填：否
     */
    private Date gmt_payment;
    /**
     * 交易退款时间
     * 是否必填：否
     */
    private Date gmt_refund;
    /**
     * 交易结束时间
     * 是否必填：否
     */
    private Date gmt_close;
    /**
     * 支付金额信息
     * 是否必填：否
     */
    private String fund_bill_list;
    /**
     * 回传参数
     * 是否必填：否
     */
    private String passback_params;
    /**
     * 优惠券信息
     * 是否必填：否
     */
    private String voucher_detail_list;
}
