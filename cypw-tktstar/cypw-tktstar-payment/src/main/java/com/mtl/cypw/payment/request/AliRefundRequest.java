package com.mtl.cypw.payment.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tang.
 * @date 2020/3/9.
 */
@Data
public class AliRefundRequest {
    public static final String refundUrl = "https://openapi.alipay.com/gateway.do";
    /**
     * 必填，商户订单号
     * 不能和 trade_no同时为空
     */
    private String out_trade_no;
    /**
     * 支付宝交易号，和商户订单号不能同时为空
     */
    private String trade_no;
    /**
     * 必填，需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     */
    private BigDecimal refund_amount;
    /**
     * 订单退款币种信息
     * 不必填
     */
    private String refund_currency;
    /**
     * 退款的原因说明
     * 不必填
     */
    private String refund_reason;
    /**
     * 标识一次退款请求，同一笔交易多次退款需要保证唯一，
     * 如需部分退款，则此参数必传。
     */
    private String out_request_no;
    /**
     * 商户的操作员编号
     * 不必填
     */
    private String operator_id;
    /**
     * 商户的门店编号
     * 不必填
     */
    private String store_id;
    /**
     * 商户的终端编号
     * 不必填
     */
    private String terminal_id;
    /**
     * 银行间联模式下有用，其它场景请不要使用；
     * 双联通过该参数指定需要退款的交易所属收单机构的pid
     * 不必填
     */
    private String org_pid;

}
