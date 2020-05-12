package com.mtl.cypw.payment.request.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2020/2/25.
 */
@Data
public class CmbRefundReqData {
    /**
     * 商户发起该请求的当前时间，精确到秒。
     * 格式：yyyyMMddHHmmss	20160623120100
     */
    private String dateTime;
    /**
     * 商户分行号
     */
    private String branchNo;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 商户订单日期，支付时的订单
     * 日期格式：yyyyMMdd	20160624
     */
    private String date;
    /**
     * 商户订单号，支付时的订单号
     */
    private String orderNo;
    /**
     * 退款流水号,商户生成，同一笔订单内，同一退款流水号只能退款一次。可用于防重复退款。
     */
    private String refundSerialNo;
    /**
     * 退款金额,格式xxxx.xx	0.01
     */
    private String amount;
    /**
     * 退款描述
     * 非必填
     */
    private String desc;
    /**
     * 商户结账系统的操作员号，选填，若填了则会对操作员号和密码进行校验，若不填则不校验。
     * 非必填
     */
    private String operatorNo;
    /**
     * 操作员密码加密算法,RC4：使用RC4算法对操作员密码进行加密，加密密钥为支付密钥。
     * DES：使用DES算法对操作员密码进行加密，加密密钥为商户支付密钥的前8位，不足8位则右补0。空：默认不加密；
     * 非必填
     */
    private String encrypType;
    /**
     * 操作员登录密码。使用encrypType算法加密后的密码
     * 注意：加密后的密文必须转换为16进制字符串表示	BF6DC5D23519
     * 非必填
     */
    private String pwd;
    /**
     * 退款标识字段
     * 空/“A”：按照订单金额发起退款（适用于自动清算/手工清算优惠交易）	可不传或refundMode=""; refundMode="A"
     * 非必填
     */
    private String refundMode;

}
